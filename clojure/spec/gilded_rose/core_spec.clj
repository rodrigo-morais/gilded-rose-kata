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
          (let [item-1 (item "Item 1" 10 0)
                updated-items (update-quality [item-1])]
            (should= {:name "Item 1" :sell-in 9 :quality 0} (first updated-items)))
        )
      )
      (describe "when quality is the maximum limit"
        (it "returns less one of quality and sell by date"
          (let [item-1 (item "Item 1" 10 QUALITY_MAX_LIMIT)
                updated-items (update-quality [item-1])]
            (should= {:name "Item 1" :sell-in 9 :quality (- QUALITY_MAX_LIMIT 1)} (first updated-items)))
        )
      )
    )
    (describe "when sell by date is in the limit"
      (describe "when quality is not in the limit"
        (it "returns less two of quality and less one of sell by date"
          (let [item-1 (item "Item 1" 0 25)
                updated-items (update-quality [item-1])]
            (should= {:name "Item 1" :sell-in -1 :quality 23} (first updated-items)))
        )
      )
      (describe "when quality is the minimum limit"
        (it "returns less one of sell by date and the minimum limit of quality"
          (let [item-1 (item "Item 1" 0 0)
                updated-items (update-quality [item-1])]
            (should= {:name "Item 1" :sell-in -1 :quality 0} (first updated-items)))
        )
      )
      (describe "when quality is the maximum limit"
        (it "returns less two of quality and less one of sell by date"
          (let [item-1 (item "Item 1" 0 QUALITY_MAX_LIMIT)
                updated-items (update-quality [item-1])]
            (should= {:name "Item 1" :sell-in -1 :quality (- QUALITY_MAX_LIMIT 2)} (first updated-items)))
        )
      )
    )
  )
  (describe "Sulfuras"
    (describe "when sell by date is not in the limit"
      (it "returns the maximum quality for Sulfuras and same sell by date"
        (let [item-1 (item SULFURAS 10 25)
              updated-items (update-quality [item-1])]
          (should= {:name SULFURAS :sell-in 10 :quality SULFURAS_QUALITY_MAX_LIMIT} (first updated-items)))
      )
    )
    (describe "when sell by date is in the limit"
      (it "returns the maximum quality for Sulfuras and same sell by date"
        (let [item-1 (item SULFURAS 0 25)
              updated-items (update-quality [item-1])]
          (should= {:name SULFURAS :sell-in 0 :quality SULFURAS_QUALITY_MAX_LIMIT} (first updated-items)))
      )
    )
  )
  (describe AGED_BRIE
    (describe "when sell by date is not in the limit"
      (describe "when quality is not in the limit"
        (it "returns more one of quality and sell by date"
          (let [item-1 (item AGED_BRIE 10 25)
                updated-items (update-quality [item-1])]
            (should= {:name AGED_BRIE :sell-in 9 :quality 26} (first updated-items)))
        )
      )
      (describe "when quality is the minimum limit"
        (it "returns less one of sell by date and one more of quality"
          (let [item-1 (item AGED_BRIE 10 0)
                updated-items (update-quality [item-1])]
            (should= {:name AGED_BRIE :sell-in 9 :quality 1} (first updated-items)))
        )
      )
      (describe "when quality is the maximum limit"
        (it "returns maximum limit of quality and sell by date"
          (let [item-1 (item AGED_BRIE 10 QUALITY_MAX_LIMIT)
                updated-items (update-quality [item-1])]
            (should= {:name AGED_BRIE :sell-in 9 :quality QUALITY_MAX_LIMIT} (first updated-items)))
        )
      )
    )
    (describe "when sell by date is in the limit"
      (describe "when quality is not in the limit"
        (it "returns two more of quality and less one of sell by date"
          (let [item-1 (item AGED_BRIE 0 25)
                updated-items (update-quality [item-1])]
            (should= {:name AGED_BRIE :sell-in -1 :quality 27} (first updated-items)))
        )
      )
      (describe "when quality is the minimum limit"
        (it "returns less one of sell by date and two more of quality"
          (let [item-1 (item AGED_BRIE 0 0)
                updated-items (update-quality [item-1])]
            (should= {:name AGED_BRIE :sell-in -1 :quality 2} (first updated-items)))
        )
      )
      (describe "when quality is the maximum limit"
        (it "returns the maximum limit of quality and less one of sell by date"
          (let [item-1 (item AGED_BRIE 0 QUALITY_MAX_LIMIT)
                updated-items (update-quality [item-1])]
            (should= {:name AGED_BRIE :sell-in -1 :quality QUALITY_MAX_LIMIT} (first updated-items)))
        )
      )
      (describe "when quality is one less the maximum limit"
        (it "returns the maximum limit of quality and less one of sell by date"
          (let [item-1 (item AGED_BRIE 0 (- QUALITY_MAX_LIMIT 1))
                updated-items (update-quality [item-1])]
            (should= {:name AGED_BRIE :sell-in -1 :quality QUALITY_MAX_LIMIT} (first updated-items)))
        )
      )
    )
  )
  (describe BACKSTAGE
    (describe "when sell by date is not in the limit"
      (describe "when quality is not in the limit"
        (it "returns more one of quality and sell by date"
          (let [item-1 (item BACKSTAGE 20 25)
                updated-items (update-quality [item-1])]
            (should= {:name BACKSTAGE :sell-in 19 :quality 26} (first updated-items)))
        )
      )
      (describe "when quality is the minimum limit"
        (it "returns less one of sell by date and one more of quality"
          (let [item-1 (item BACKSTAGE 20 0)
                updated-items (update-quality [item-1])]
            (should= {:name BACKSTAGE :sell-in 19 :quality 1} (first updated-items)))
        )
      )
      (describe "when quality is the maximum limit"
        (it "returns maximum limit of quality and sell by date"
          (let [item-1 (item BACKSTAGE 20 QUALITY_MAX_LIMIT)
                updated-items (update-quality [item-1])]
            (should= {:name BACKSTAGE :sell-in 19 :quality QUALITY_MAX_LIMIT} (first updated-items)))
        )
      )
    )
    (describe "when sell by date is in the limit"
      (describe "when quality is not in the limit"
        (it "returns the minimum limit of quality and less one of sell by date"
          (let [item-1 (item BACKSTAGE 0 25)
                updated-items (update-quality [item-1])]
            (should= {:name BACKSTAGE :sell-in -1 :quality 0} (first updated-items)))
        )
      )
      (describe "when quality is the minimum limit"
        (it "returns less one of sell by date and the minimum limit of quality"
          (let [item-1 (item BACKSTAGE 0 0)
                updated-items (update-quality [item-1])]
            (should= {:name BACKSTAGE :sell-in -1 :quality 0} (first updated-items)))
        )
      )
      (describe "when quality is the maximum limit"
        (it "returns the minimum limit of quality and less one of sell by date"
          (let [item-1 (item BACKSTAGE 0 QUALITY_MAX_LIMIT)
                updated-items (update-quality [item-1])]
            (should= {:name BACKSTAGE :sell-in -1 :quality 0} (first updated-items)))
        )
      )
    )
    (describe "when sell by date is less or equal 10 and more than 5"
      (describe "when quality is not in the limit"
        (it "returns two more of quality and less one of sell by date"
          (let [item-1 (item BACKSTAGE 10 25)
                item-2 (item BACKSTAGE 6 29)
                updated-items (update-quality [item-1 item-2])]
            (should= {:name BACKSTAGE :sell-in 9 :quality 27} (first updated-items))
            (should= {:name BACKSTAGE :sell-in 5 :quality 31} (second updated-items)))
        )
      )
      (describe "when quality is the minimum limit"
        (it "returns less one of sell by date and two more of quality"
          (let [item-1 (item BACKSTAGE 10 0)
                item-2 (item BACKSTAGE 6 0)
                updated-items (update-quality [item-1 item-2])]
            (should= {:name BACKSTAGE :sell-in 9 :quality 2} (first updated-items))
            (should= {:name BACKSTAGE :sell-in 5 :quality 2} (second updated-items)))
        )
      )
      (describe "when quality is the maximum limit"
        (it "returns the maximum limit of quality and less one of sell by date"
          (let [item-1 (item BACKSTAGE 10 QUALITY_MAX_LIMIT)
                item-2 (item BACKSTAGE 6 QUALITY_MAX_LIMIT)
                updated-items (update-quality [item-1 item-2])]
            (should= {:name BACKSTAGE :sell-in 9 :quality QUALITY_MAX_LIMIT} (first updated-items))
            (should= {:name BACKSTAGE :sell-in 5 :quality QUALITY_MAX_LIMIT} (second updated-items)))
        )
      )
      (describe "when quality is one less than the maximum limit"
        (it "returns the maximum limit of quality and less one of sell by date"
          (let [item-1 (item BACKSTAGE 10 (- QUALITY_MAX_LIMIT 1))
                item-2 (item BACKSTAGE 6 (- QUALITY_MAX_LIMIT 1))
                updated-items (update-quality [item-1 item-2])]
            (should= {:name BACKSTAGE :sell-in 9 :quality QUALITY_MAX_LIMIT} (first updated-items))
            (should= {:name BACKSTAGE :sell-in 5 :quality QUALITY_MAX_LIMIT} (second updated-items)))
        )
      )
    )
    (describe "when sell by date is less or equal 5 and more than 0"
      (describe "when quality is not in the limit"
        (it "returns three more of quality and less one of sell by date"
          (let [item-1 (item BACKSTAGE 5 25)
                item-2 (item BACKSTAGE 1 29)
                updated-items (update-quality [item-1 item-2])]
            (should= {:name BACKSTAGE :sell-in 4 :quality 28} (first updated-items))
            (should= {:name BACKSTAGE :sell-in 0 :quality 32} (second updated-items)))
        )
      )
      (describe "when quality is the minimum limit"
        (it "returns less one of sell by date and three more of quality"
          (let [item-1 (item BACKSTAGE 5 0)
                item-2 (item BACKSTAGE 1 0)
                updated-items (update-quality [item-1 item-2])]
            (should= {:name BACKSTAGE :sell-in 4 :quality 3} (first updated-items))
            (should= {:name BACKSTAGE :sell-in 0 :quality 3} (second updated-items)))
        )
      )
      (describe "when quality is the maximum limit"
        (it "returns the maximum limit of quality and less one of sell by date"
          (let [item-1 (item BACKSTAGE 5 QUALITY_MAX_LIMIT)
                item-2 (item BACKSTAGE 1 QUALITY_MAX_LIMIT)
                updated-items (update-quality [item-1 item-2])]
            (should= {:name BACKSTAGE :sell-in 4 :quality QUALITY_MAX_LIMIT} (first updated-items))
            (should= {:name BACKSTAGE :sell-in 0 :quality QUALITY_MAX_LIMIT} (second updated-items)))
        )
      )
      (describe "when quality is one less than the maximum limit"
        (it "returns the maximum limit of quality and less one of sell by date"
          (let [item-1 (item BACKSTAGE 5 (- QUALITY_MAX_LIMIT 1))
                item-2 (item BACKSTAGE 1 (- QUALITY_MAX_LIMIT 1))
                updated-items (update-quality [item-1 item-2])]
            (should= {:name BACKSTAGE :sell-in 4 :quality QUALITY_MAX_LIMIT} (first updated-items))
            (should= {:name BACKSTAGE :sell-in 0 :quality QUALITY_MAX_LIMIT} (second updated-items)))
        )
      )
      (describe "when quality is two less than the maximum limit"
        (it "returns the maximum limit of quality and less one of sell by date"
          (let [item-1 (item BACKSTAGE 5 (- QUALITY_MAX_LIMIT 2))
                item-2 (item BACKSTAGE 1 (- QUALITY_MAX_LIMIT 2))
                updated-items (update-quality [item-1 item-2])]
            (should= {:name BACKSTAGE :sell-in 4 :quality QUALITY_MAX_LIMIT} (first updated-items))
            (should= {:name BACKSTAGE :sell-in 0 :quality QUALITY_MAX_LIMIT} (second updated-items)))
        )
      )
    )
  )
)
