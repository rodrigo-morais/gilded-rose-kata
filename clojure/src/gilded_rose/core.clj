(ns gilded-rose.core
(:require [gilded-rose.constants :refer :all]))

(defn update-items-quality [item]
  (cond
    (and (< (:sell-in item) SELL_IN_LIMIT) (= BACKSTAGE (:name item)))
      (merge item {:quality QUALITY_MIN_LIMIT})
    (= (:name item) AGED_BRIE)
      (if (< (:sell-in item) SELL_IN_LIMIT)
        (if (< (:quality item) (- QUALITY_MAX_LIMIT 1))
          (merge item {:quality (inc (inc (:quality item)))})
          (merge item {:quality QUALITY_MAX_LIMIT}))
        (if (< (:quality item) QUALITY_MAX_LIMIT)
          (merge item {:quality (inc (:quality item))})
          (merge item {:quality QUALITY_MAX_LIMIT})))
    (= (:name item) BACKSTAGE)
      (if (and (= (:name item) BACKSTAGE) (>= (:sell-in item) 5) (< (:sell-in item) 10))
        (if (< (:quality item) (- QUALITY_MAX_LIMIT 1))
          (merge item {:quality (inc (inc (:quality item)))})
          (merge item {:quality QUALITY_MAX_LIMIT}))
        (if (and (= (:name item) BACKSTAGE) (>= (:sell-in item) SELL_IN_LIMIT) (< (:sell-in item) 5))
        (if (< (:quality item) (- QUALITY_MAX_LIMIT 2))
          (merge item {:quality (inc (inc (inc (:quality item))))})
          (merge item {:quality QUALITY_MAX_LIMIT}))
          (if (< (:quality item) QUALITY_MAX_LIMIT)
            (merge item {:quality (inc (:quality item))})
            item)))
    (< (:sell-in item) SELL_IN_LIMIT)
      (if (= BACKSTAGE (:name item))
        (merge item {:quality QUALITY_MIN_LIMIT})
        (if (<= (dec (dec (:quality item))) QUALITY_MIN_LIMIT)
          (merge item {:quality QUALITY_MIN_LIMIT})
          (merge item {:quality (dec (dec (:quality item)))})))
    (or (= "+5 Dexterity Vest" (:name item)) (= "Elixir of the Mongoose" (:name item)))
      (merge item {:quality (dec (:quality item))})
    (= SULFURAS (:name item))
      (merge item {:quality SULFURAS_QUALITY_MAX_LIMIT})
    (or (<= (:quality item) QUALITY_MIN_LIMIT))
      (merge item {:quality QUALITY_MIN_LIMIT})
    :else (merge item {:quality (dec (:quality item))})
  )
)

(defn update-sell-in [item]
  (if (not= SULFURAS (:name item))
    (merge item {:sell-in (dec (:sell-in item))})
  item)
)

(defn update-quality [items]
  (map (comp update-items-quality update-sell-in) items)
)

(defn item [item-name, sell-in, quality]
  {:name item-name, :sell-in sell-in, :quality quality})

(defn update-current-inventory[]
  (let [inventory 
    [
      (item "+5 Dexterity Vest" 10 20)
      (item AGED_BRIE 2 0)
      (item "Elixir of the Mongoose" 5 7)
      (item "Sulfuras, Hand Of Ragnaros" 0 SULFURAS_QUALITY_MAX_LIMIT)
      (item BACKSTAGE 15 20)
    ]]
    (update-quality inventory)
    ))
