module GildedRoseKata exposing (updateQuality)


import Guards exposing ((|=), (=>))
import Models exposing (GildedRose, SellIn, Quality, Step, Item)
import Constants exposing (sellInLimit, maxQualityLimit, backspaceMinQualityLimit, sulfurasMaxQualityLimit, standardStep)


updateQuality : GildedRose -> GildedRose
updateQuality =
    List.map updateItem

    
updateItem : Item -> Item
updateItem item =
  let
    sellIn_ = updateSellInItem item.name item.sellIn

    quality_ = updateQualityItem { item | sellIn = sellIn_ }
  in
    { item | sellIn = sellIn_, quality = quality_ }


updateSellInItem : String -> SellIn -> SellIn
updateSellInItem name sellIn =
  case name of
    "Sulfuras" -> sellIn
    _ -> sellIn - 1


updateQualityItem : Item -> Quality
updateQualityItem item =
  let
    step = getStep item
  in
    case item.name of
      "Sulfuras" -> sulfurasMaxQualityLimit
      "Aged Brie" -> getAgedBrieQuality item step
      "Backstage passes" -> getBackstageQuality item step
      _ -> getStandardQuality item step


getStep : Item -> Step
getStep item =
  case item.name of
    "Backstage passes" -> (item.sellIn < 6 => 3
                          |= item.sellIn < 10 => 2
                          |= standardStep)
    "Conjured" -> (item.sellIn < sellInLimit => standardStep * 4
                  |= standardStep * 2)
    _ -> (item.sellIn < sellInLimit => standardStep * 2
         |= standardStep)


getStandardQuality : Item -> Step -> Quality
getStandardQuality item step =
  item.quality - step > 0 => item.quality - step
  |= 0


getIncreaseQuality : Item -> Step -> Quality
getIncreaseQuality item step =
  item.quality + step < maxQualityLimit => item.quality + step
  |= maxQualityLimit


getAgedBrieQuality : Item -> Step -> Quality
getAgedBrieQuality item step = getIncreaseQuality item step


getBackstageQuality : Item -> Step -> Quality
getBackstageQuality item step =
  item.sellIn <= 0 => 0
  |= getIncreaseQuality item step
