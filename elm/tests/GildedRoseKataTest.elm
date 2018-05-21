module GildedRoseKataTest exposing (..)

import Test exposing (..)
import Expect

import GildedRoseKata


all : Test
all =
  describe "Gilded Rose Kata"
    [ describe "First"
      [ test "first" <|
        \() ->
          let
            expected = ""
          in
            Expect.equal (GildedRoseKata.updateQuality []) expected
      ]
    ]
