module GildedRoseKata exposing (Item(Item), updateQuality)


type alias GildedRose =
    List Item


type Item
    = Item String Int Int

updateQuality : GildedRose -> GildedRose
updateQuality =
    List.map updateItem
    
updateItem : Item -> Item
updateItem item =
  item
  |> updateSellIn
  |> updateQualityItem

updateSellIn : Item -> Item
updateSellIn (Item name sellIn quality) =
  case name of
    "Sulfuras" -> (Item name sellIn quality)
    _ -> (Item name (sellIn - 1) quality)
  
updateQualityItem : Item -> Item
updateQualityItem (Item name sellIn quality) =
    let
        quality_ =
            if (name /= "Aged Brie" && name /= "Backstage passes") then
                if quality > 0 then
                    if name /= "Sulfuras" then
                        quality - 1
                    else
                        quality
                else
                    quality
            else if quality < 50 then
                quality
                    + 1
                    + (if name == "Backstage passes" then
                        if sellIn < 10 then
                            if quality < 49 then
                                1
                                    + (if sellIn < 5 then
                                        if quality < 48 then
                                            1
                                        else
                                            0
                                       else
                                        0
                                      )
                            else
                                0
                        else
                            0
                       else
                        0
                      )
            else
                quality

    in
        if sellIn < 0 then
            if name /= "Aged Brie" then
                if name /= "Backstage passes" then
                    if quality_ > 0 then
                        if name /= "Sulfuras" then
                            (Item name sellIn (quality_ - 1))
                        else
                            (Item name sellIn quality_)
                    else
                        (Item name sellIn quality_)
                else
                    (Item name sellIn (quality_ - quality_))
            else if quality_ < 50 then
                (Item name sellIn (quality_ + 1))
            else
                (Item name sellIn quality_)
        else
            (Item name sellIn quality_)
