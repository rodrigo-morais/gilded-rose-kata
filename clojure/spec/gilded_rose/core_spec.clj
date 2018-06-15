(ns gilded-rose.core-spec
(:require [clojure.test :refer :all]
          [speclj.core :refer :all]
          [gilded-rose.constants :refer :all]
          [gilded-rose.core :refer :all]))

(describe "Gilded Rose"
  (describe "Standard Item"
    (describe "when sell by date is not in the limit"
      (describe "when quality is not in the limit"
        (it "returns less one of quality and sell by date"
          (let [item-1 (item "Item 1" 10 25)
                item-2 (item "Item 2" 11 22)
                updated-items (update-quality [item-1 item-2])]
            (should= {:name "Item 1" :sell-in 9 :quality 24} (first updated-items))
            (should= {:name "Item 2" :sell-in 10 :quality 21} (second updated-items)))
        )
      )
      (describe "when quality is the minimum limit"
        (it "returns less one of sell by date and the minimum limit of quality"
          (let [item-1 (item "Item 1" 10 quality-min-limit)
                updated-items (update-quality [item-1])]
            (should= {:name "Item 1" :sell-in 9 :quality quality-min-limit} (first updated-items)))
        )
      )
      (describe "when quality is the maximum limit"
        (it "returns less one of quality and sell by date"
          (let [item-1 (item "Item 1" 10 quality-max-limit)
                updated-items (update-quality [item-1])]
            (should= {:name "Item 1" :sell-in 9 :quality (- quality-max-limit 1)} (first updated-items)))
        )
      )
    )
    (describe "when sell by date is in the limit"
      (describe "when quality is not in the limit"
        (it "returns less two of quality and less one of sell by date"
          (let [item-1 (item "Item 1" sell-in-limit 25)
                updated-items (update-quality [item-1])]
            (should= {:name "Item 1" :sell-in -1 :quality 23} (first updated-items)))
        )
      )
      (describe "when quality is the minimum limit"
        (it "returns less one of sell by date and the minimum limit of quality"
          (let [item-1 (item "Item 1" sell-in-limit quality-min-limit)
                updated-items (update-quality [item-1])]
            (should= {:name "Item 1" :sell-in -1 :quality quality-min-limit} (first updated-items)))
        )
      )
      (describe "when quality is the maximum limit"
        (it "returns less two of quality and less one of sell by date"
          (let [item-1 (item "Item 1" sell-in-limit quality-max-limit)
                updated-items (update-quality [item-1])]
            (should= {:name "Item 1" :sell-in -1 :quality (- quality-max-limit 2)} (first updated-items)))
        )
      )
    )
  )
  (describe "Sulfuras"
    (describe "when sell by date is not in the limit"
      (it "returns the maximum quality for Sulfuras and same sell by date"
        (let [item-1 (item sulfuras 10 25)
              updated-items (update-quality [item-1])]
          (should= {:name sulfuras :sell-in 10 :quality sulfuras-quality-max-limit} (first updated-items)))
      )
    )
    (describe "when sell by date is in the limit"
      (it "returns the maximum quality for Sulfuras and same sell by date"
        (let [item-1 (item sulfuras sell-in-limit 25)
              updated-items (update-quality [item-1])]
          (should= {:name sulfuras :sell-in sell-in-limit :quality sulfuras-quality-max-limit} (first updated-items)))
      )
    )
  )
  (describe aged-brie
    (describe "when sell by date is not in the limit"
      (describe "when quality is not in the limit"
        (it "returns more one of quality and sell by date"
          (let [item-1 (item aged-brie 10 25)
                updated-items (update-quality [item-1])]
            (should= {:name aged-brie :sell-in 9 :quality 26} (first updated-items)))
        )
      )
      (describe "when quality is the minimum limit"
        (it "returns less one of sell by date and one more of quality"
          (let [item-1 (item aged-brie 10 quality-min-limit)
                updated-items (update-quality [item-1])]
            (should= {:name aged-brie :sell-in 9 :quality 1} (first updated-items)))
        )
      )
      (describe "when quality is the maximum limit"
        (it "returns maximum limit of quality and sell by date"
          (let [item-1 (item aged-brie 10 quality-max-limit)
                updated-items (update-quality [item-1])]
            (should= {:name aged-brie :sell-in 9 :quality quality-max-limit} (first updated-items)))
        )
      )
    )
    (describe "when sell by date is in the limit"
      (describe "when quality is not in the limit"
        (it "returns two more of quality and less one of sell by date"
          (let [item-1 (item aged-brie sell-in-limit 25)
                updated-items (update-quality [item-1])]
            (should= {:name aged-brie :sell-in -1 :quality 27} (first updated-items)))
        )
      )
      (describe "when quality is the minimum limit"
        (it "returns less one of sell by date and two more of quality"
          (let [item-1 (item aged-brie sell-in-limit quality-min-limit)
                updated-items (update-quality [item-1])]
            (should= {:name aged-brie :sell-in -1 :quality 2} (first updated-items)))
        )
      )
      (describe "when quality is the maximum limit"
        (it "returns the maximum limit of quality and less one of sell by date"
          (let [item-1 (item aged-brie sell-in-limit quality-max-limit)
                updated-items (update-quality [item-1])]
            (should= {:name aged-brie :sell-in -1 :quality quality-max-limit} (first updated-items)))
        )
      )
      (describe "when quality is one less the maximum limit"
        (it "returns the maximum limit of quality and less one of sell by date"
          (let [item-1 (item aged-brie sell-in-limit (- quality-max-limit 1))
                updated-items (update-quality [item-1])]
            (should= {:name aged-brie :sell-in -1 :quality quality-max-limit} (first updated-items)))
        )
      )
    )
  )
  (describe backstage
    (describe "when sell by date is not in the limit"
      (describe "when quality is not in the limit"
        (it "returns more one of quality and sell by date"
          (let [item-1 (item backstage 20 25)
                updated-items (update-quality [item-1])]
            (should= {:name backstage :sell-in 19 :quality 26} (first updated-items)))
        )
      )
      (describe "when quality is the minimum limit"
        (it "returns less one of sell by date and one more of quality"
          (let [item-1 (item backstage 20 quality-min-limit)
                updated-items (update-quality [item-1])]
            (should= {:name backstage :sell-in 19 :quality 1} (first updated-items)))
        )
      )
      (describe "when quality is the maximum limit"
        (it "returns maximum limit of quality and sell by date"
          (let [item-1 (item backstage 20 quality-max-limit)
                updated-items (update-quality [item-1])]
            (should= {:name backstage :sell-in 19 :quality quality-max-limit} (first updated-items)))
        )
      )
    )
    (describe "when sell by date is in the limit"
      (describe "when quality is not in the limit"
        (it "returns the minimum limit of quality and less one of sell by date"
          (let [item-1 (item backstage sell-in-limit 25)
                updated-items (update-quality [item-1])]
            (should= {:name backstage :sell-in -1 :quality quality-min-limit} (first updated-items)))
        )
      )
      (describe "when quality is the minimum limit"
        (it "returns less one of sell by date and the minimum limit of quality"
          (let [item-1 (item backstage sell-in-limit quality-min-limit)
                updated-items (update-quality [item-1])]
            (should= {:name backstage :sell-in -1 :quality quality-min-limit} (first updated-items)))
        )
      )
      (describe "when quality is the maximum limit"
        (it "returns the minimum limit of quality and less one of sell by date"
          (let [item-1 (item backstage sell-in-limit quality-max-limit)
                updated-items (update-quality [item-1])]
            (should= {:name backstage :sell-in -1 :quality quality-min-limit} (first updated-items)))
        )
      )
    )
    (describe "when sell by date is less or equal 10 and more than 5"
      (describe "when quality is not in the limit"
        (it "returns two more of quality and less one of sell by date"
          (let [item-1 (item backstage 10 25)
                item-2 (item backstage 6 29)
                updated-items (update-quality [item-1 item-2])]
            (should= {:name backstage :sell-in 9 :quality 27} (first updated-items))
            (should= {:name backstage :sell-in 5 :quality 31} (second updated-items)))
        )
      )
      (describe "when quality is the minimum limit"
        (it "returns less one of sell by date and two more of quality"
          (let [item-1 (item backstage 10 quality-min-limit)
                item-2 (item backstage 6 quality-min-limit)
                updated-items (update-quality [item-1 item-2])]
            (should= {:name backstage :sell-in 9 :quality 2} (first updated-items))
            (should= {:name backstage :sell-in 5 :quality 2} (second updated-items)))
        )
      )
      (describe "when quality is the maximum limit"
        (it "returns the maximum limit of quality and less one of sell by date"
          (let [item-1 (item backstage 10 quality-max-limit)
                item-2 (item backstage 6 quality-max-limit)
                updated-items (update-quality [item-1 item-2])]
            (should= {:name backstage :sell-in 9 :quality quality-max-limit} (first updated-items))
            (should= {:name backstage :sell-in 5 :quality quality-max-limit} (second updated-items)))
        )
      )
      (describe "when quality is one less than the maximum limit"
        (it "returns the maximum limit of quality and less one of sell by date"
          (let [item-1 (item backstage 10 (- quality-max-limit 1))
                item-2 (item backstage 6 (- quality-max-limit 1))
                updated-items (update-quality [item-1 item-2])]
            (should= {:name backstage :sell-in 9 :quality quality-max-limit} (first updated-items))
            (should= {:name backstage :sell-in 5 :quality quality-max-limit} (second updated-items)))
        )
      )
    )
    (describe "when sell by date is less or equal 5 and more than 0"
      (describe "when quality is not in the limit"
        (it "returns three more of quality and less one of sell by date"
          (let [item-1 (item backstage 5 25)
                item-2 (item backstage 1 29)
                updated-items (update-quality [item-1 item-2])]
            (should= {:name backstage :sell-in 4 :quality 28} (first updated-items))
            (should= {:name backstage :sell-in 0 :quality 32} (second updated-items)))
        )
      )
      (describe "when quality is the minimum limit"
        (it "returns less one of sell by date and three more of quality"
          (let [item-1 (item backstage 5 quality-min-limit)
                item-2 (item backstage 1 quality-min-limit)
                updated-items (update-quality [item-1 item-2])]
            (should= {:name backstage :sell-in 4 :quality 3} (first updated-items))
            (should= {:name backstage :sell-in 0 :quality 3} (second updated-items)))
        )
      )
      (describe "when quality is the maximum limit"
        (it "returns the maximum limit of quality and less one of sell by date"
          (let [item-1 (item backstage 5 quality-max-limit)
                item-2 (item backstage 1 quality-max-limit)
                updated-items (update-quality [item-1 item-2])]
            (should= {:name backstage :sell-in 4 :quality quality-max-limit} (first updated-items))
            (should= {:name backstage :sell-in 0 :quality quality-max-limit} (second updated-items)))
        )
      )
      (describe "when quality is one less than the maximum limit"
        (it "returns the maximum limit of quality and less one of sell by date"
          (let [item-1 (item backstage 5 (- quality-max-limit 1))
                item-2 (item backstage 1 (- quality-max-limit 1))
                updated-items (update-quality [item-1 item-2])]
            (should= {:name backstage :sell-in 4 :quality quality-max-limit} (first updated-items))
            (should= {:name backstage :sell-in 0 :quality quality-max-limit} (second updated-items)))
        )
      )
      (describe "when quality is two less than the maximum limit"
        (it "returns the maximum limit of quality and less one of sell by date"
          (let [item-1 (item backstage 5 (- quality-max-limit 2))
                item-2 (item backstage 1 (- quality-max-limit 2))
                updated-items (update-quality [item-1 item-2])]
            (should= {:name backstage :sell-in 4 :quality quality-max-limit} (first updated-items))
            (should= {:name backstage :sell-in 0 :quality quality-max-limit} (second updated-items)))
        )
      )
    )
  )
  (describe conjured
    (describe "when sell by date is not in the limit"
      (describe "when quality is not in the limit"
        (it "returns less one of quality and sell by date"
          (let [item-1 (item conjured 10 25)
                updated-items (update-quality [item-1])]
            (should= {:name conjured :sell-in 9 :quality 23} (first updated-items)))
        )
      )
      (describe "when quality is the minimum limit"
        (it "returns less one of sell by date and the minimum limit of quality"
          (let [item-1 (item conjured 10 quality-min-limit)
                updated-items (update-quality [item-1])]
            (should= {:name conjured :sell-in 9 :quality quality-min-limit} (first updated-items)))
        )
      )
      (describe "when quality is the maximum limit"
        (it "returns less one of quality and sell by date"
          (let [item-1 (item conjured 10 quality-max-limit)
                updated-items (update-quality [item-1])]
            (should= {:name conjured :sell-in 9 :quality (- quality-max-limit 2)} (first updated-items)))
        )
      )
    )
    (describe "when sell by date is in the limit"
      (describe "when quality is not in the limit"
        (it "returns less two of quality and less one of sell by date"
          (let [item-1 (item conjured sell-in-limit 25)
                updated-items (update-quality [item-1])]
            (should= {:name conjured :sell-in -1 :quality 21} (first updated-items)))
        )
      )
      (describe "when quality is the minimum limit"
        (it "returns less one of sell by date and the minimum limit of quality"
          (let [item-1 (item conjured sell-in-limit quality-min-limit)
                updated-items (update-quality [item-1])]
            (should= {:name conjured :sell-in -1 :quality quality-min-limit} (first updated-items)))
        )
      )
      (describe "when quality is the maximum limit"
        (it "returns less two of quality and less one of sell by date"
          (let [item-1 (item conjured sell-in-limit quality-max-limit)
                updated-items (update-quality [item-1])]
            (should= {:name conjured :sell-in -1 :quality (- quality-max-limit 4)} (first updated-items)))
        )
      )
    )
  )
)
