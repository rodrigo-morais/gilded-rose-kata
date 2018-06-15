(ns gilded-rose.core
(:require [gilded-rose.constants :refer :all]))

(defn pass-off-sell-date [item]
  (< (:sell-in item) sell-in-limit))

(defmulti get-step (fn [item] (item :name)))

(defmethod get-step backstage [item]
  (cond
    (< (:sell-in item) 5) 3
    (< (:sell-in item) 10) 2
    :else 1))

(defmethod get-step :default [item]
  (if (pass-off-sell-date item) (* standard-step 2) standard-step))

(defmulti update-item-quality (fn [step item] (item :name)))

(defmethod update-item-quality sulfuras [step item]
  (merge item {:quality 80}))

(defmethod update-item-quality aged-brie [step item]
  (merge item {:quality (min quality-max-limit (+ (:quality item) step))}))

(defmethod update-item-quality backstage [step item]
  (cond
    (< (:sell-in item) sell-in-limit) (merge item {:quality quality-min-limit})
    :else (merge item {:quality (min quality-max-limit (+ (:quality item) step))})))

(defmethod update-item-quality conjured [step item]
  (merge item {:quality (max quality-min-limit (- (:quality item) (* step 2)))}))

(defmethod update-item-quality :default [step item]
  (merge item {:quality (max quality-min-limit (- (:quality item) step))}))

(defn update-sell-in [item]
  (if (not= sulfuras (:name item))
    (merge item {:sell-in (dec (:sell-in item))})
    item)
)

(defn update-item [item]
  (update-item-quality (get-step item) item))

(defn update-quality [items]
  (map (comp update-item update-sell-in) items)
)

(defn item [item-name, sell-in, quality]
  {:name item-name, :sell-in sell-in, :quality quality})

(defn update-current-inventory[]
  (let [inventory 
    [
      (item "+5 Dexterity Vest" 10 20)
      (item aged-brie 2 0)
      (item "Elixir of the Mongoose" 5 7)
      (item "Sulfuras, Hand Of Ragnaros" 0 sulfuras-quality-max-limit)
      (item backstage 15 20)
    ]]
    (update-quality inventory)
    ))
