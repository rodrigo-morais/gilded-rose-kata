(ns gilded-rose.core-spec
(:require [clojure.test :refer :all]
          [speclj.core :refer :all]
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
          (let [item-1 (item "Item 1" 10 50)
                updated-items (update-quality [item-1])]
            (should= {:name "Item 1" :sell-in 9 :quality 49} (first updated-items)))
        )
      )
    )
    (describe "when sell by date is in the limit"
      (describe "when quality is not in the limit"
        (it "returns less two of quality and less one ofsell by date"
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
          (let [item-1 (item "Item 1" 0 50)
                updated-items (update-quality [item-1])]
            (should= {:name "Item 1" :sell-in -1 :quality 48} (first updated-items)))
        )
      )
    )
  )
  (describe "Sulfuras"
    (describe "when sell by date is not in the limit"
      (it "returns the maximum quality for Sulfuras and same sell by date"
        (let [item-1 (item "Sulfuras, Hand of Ragnaros" 10 25)
              updated-items (update-quality [item-1])]
          (should= {:name "Sulfuras, Hand of Ragnaros" :sell-in 10 :quality 80} (first updated-items)))
      )
    )
    (describe "when sell by date is in the limit"
      (it "returns the maximum quality for Sulfuras and same sell by date"
        (let [item-1 (item "Sulfuras, Hand of Ragnaros" 0 25)
              updated-items (update-quality [item-1])]
          (should= {:name "Sulfuras, Hand of Ragnaros" :sell-in 0 :quality 80} (first updated-items)))
      )
    )
  )
  (describe "Aged Brie"
    (describe "when sell by date is not in the limit"
      (describe "when quality is not in the limit"
        (it "returns more one of quality and sell by date"
          (let [item-1 (item "Aged Brie" 10 25)
                updated-items (update-quality [item-1])]
            (should= {:name "Aged Brie" :sell-in 9 :quality 26} (first updated-items)))
        )
      )
      (describe "when quality is the minimum limit"
        (it "returns less one of sell by date and one more of quality"
          (let [item-1 (item "Aged Brie" 10 0)
                updated-items (update-quality [item-1])]
            (should= {:name "Aged Brie" :sell-in 9 :quality 1} (first updated-items)))
        )
      )
      (describe "when quality is the maximum limit"
        (it "returns maximum limit of quality and sell by date"
          (let [item-1 (item "Aged Brie" 10 50)
                updated-items (update-quality [item-1])]
            (should= {:name "Aged Brie" :sell-in 9 :quality 50} (first updated-items)))
        )
      )
    )
    (describe "when sell by date is in the limit"
      (describe "when quality is not in the limit"
        (it "returns two more of quality and less one ofsell by date"
          (let [item-1 (item "Aged Brie" 0 25)
                updated-items (update-quality [item-1])]
            (should= {:name "Aged Brie" :sell-in -1 :quality 27} (first updated-items)))
        )
      )
      (describe "when quality is the minimum limit"
        (it "returns less one of sell by date and two more of quality"
          (let [item-1 (item "Aged Brie" 0 0)
                updated-items (update-quality [item-1])]
            (should= {:name "Aged Brie" :sell-in -1 :quality 2} (first updated-items)))
        )
      )
      (describe "when quality is the maximum limit"
        (it "returns the maximum limit of quality and less one of sell by date"
          (let [item-1 (item "Aged Brie" 0 50)
                updated-items (update-quality [item-1])]
            (should= {:name "Aged Brie" :sell-in -1 :quality 50} (first updated-items)))
        )
      )
      (describe "when quality is one less the maximum limit"
        (it "returns the maximum limit of quality and less one of sell by date"
          (let [item-1 (item "Aged Brie" 0 49)
                updated-items (update-quality [item-1])]
            (should= {:name "Aged Brie" :sell-in -1 :quality 50} (first updated-items)))
        )
      )
    )
  )
)
