module Models exposing (..)


type alias GildedRose =
    List Item


type alias SellIn = Int


type alias Quality = Int


type alias Step = Int


type alias Item
    =
    { name : String
    , sellIn : SellIn
    , quality : Quality
    }
