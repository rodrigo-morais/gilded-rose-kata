(ns gilded-rose.core)

(defn update-quality [items]
  (map
    (fn[item] (cond
      (and (< (:sell-in item) 0) (= "Backstage passes to a TAFKAL80ETC concert" (:name item)))
        (merge item {:quality 0})
      (= (:name item) "Aged Brie")
        (if (< (:sell-in item) 0)
          (if (< (:quality item) 49)
            (merge item {:quality (inc (inc (:quality item)))})
            (merge item {:quality 50}))
          (if (< (:quality item) 50)
            (merge item {:quality (inc (:quality item))})
            (merge item {:quality 50})))
      (= (:name item) "Backstage passes to a TAFKAL80ETC concert")
        (if (and (= (:name item) "Backstage passes to a TAFKAL80ETC concert") (>= (:sell-in item) 5) (< (:sell-in item) 10))
          (if (< (:quality item) 49)
            (merge item {:quality (inc (inc (:quality item)))})
            (merge item {:quality 50}))
          (if (and (= (:name item) "Backstage passes to a TAFKAL80ETC concert") (>= (:sell-in item) 0) (< (:sell-in item) 5))
          (if (< (:quality item) 48)
            (merge item {:quality (inc (inc (inc (:quality item))))})
            (merge item {:quality 50}))
            (if (< (:quality item) 50)
              (merge item {:quality (inc (:quality item))})
              item)))
      (< (:sell-in item) 0)
        (if (= "Backstage passes to a TAFKAL80ETC concert" (:name item))
          (merge item {:quality 0})
          (if (<= (dec (dec (:quality item))) 0)
            (merge item {:quality 0})
            (merge item {:quality (dec (dec (:quality item)))})))
      (or (= "+5 Dexterity Vest" (:name item)) (= "Elixir of the Mongoose" (:name item)))
        (merge item {:quality (dec (:quality item))})
      (= "Sulfuras, Hand of Ragnaros" (:name item))
        (merge item {:quality 80})
      (or (<= (:quality item) 0))
        (merge item {:quality 0})
      :else (merge item {:quality (dec (:quality item))})))
  (map (fn [item]
      (if (not= "Sulfuras, Hand of Ragnaros" (:name item))
        (merge item {:sell-in (dec (:sell-in item))})
        item))
  items)))

(defn item [item-name, sell-in, quality]
  {:name item-name, :sell-in sell-in, :quality quality})

(defn update-current-inventory[]
  (let [inventory 
    [
      (item "+5 Dexterity Vest" 10 20)
      (item "Aged Brie" 2 0)
      (item "Elixir of the Mongoose" 5 7)
      (item "Sulfuras, Hand Of Ragnaros" 0 80)
      (item "Backstage passes to a TAFKAL80ETC concert" 15 20)
    ]]
    (update-quality inventory)
    ))
