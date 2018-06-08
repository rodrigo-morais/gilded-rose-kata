module GildedRoseKataTest exposing (..)

import Test exposing (..)
import Expect

import GildedRoseKata exposing (updateQuality)
import Models exposing (Item)


all : Test
all =
  describe "Gilded Rose Kata"
    [ describe "#updateQualite"
      [ describe "when items are empty"
        [ test "returns empty array of items" <|
          \() ->
            let
              expected = []
            in
              Expect.equal (updateQuality []) expected
        ],
        describe "standard item"
          [ describe "when sell_in is up to the limit"
            [  describe "when items has no limits of quality"
              [ test "returns items with less quality and sell_in" <|
                \() ->
                  let
                    items = [Item "standard item" 11 26]
                    expected = [Item "standard item" 10 25]
                  in
                    Expect.equal (updateQuality items) expected
              ],
              describe "when limit the quality is the maximum"
              [ test "returns items with less quality and sell_in" <|
                \() ->
                  let
                    items = [
                      Item "standard item 1" 11 50,
                      Item "standard item 2" 13 50
                    ]
                    expected = [
                      Item "standard item 1" 10 49,
                      Item "standard item 2" 12 49
                    ]
                  in
                    Expect.equal (updateQuality items) expected
              ],
              describe "when limit the quality is the minimum"
              [ test "returns items with less sell_in and same quality" <|
                \() ->
                  let
                    items = [
                      Item "standard item 1" 11 0,
                      Item "standard item 2" 13 0
                    ]
                    expected = [
                      Item "standard item 1" 10 0,
                      Item "standard item 2" 12 0
                    ]
                  in
                    Expect.equal (updateQuality items) expected
              ]
            ], 
            describe "when sell_in is in the limit"
            [  describe "when items has no limits of quality"
              [ test "returns items with less quality and sell_in" <|
                \() ->
                  let
                    items = [Item "standard item" 0 25]
                    expected = [Item "standard item" -1 23]
                  in
                    Expect.equal (updateQuality items) expected
              ],
              describe "when limit the quality is the maximum"
              [ test "returns items with less quality and sell_in" <|
                \() ->
                  let
                    items = [
                      Item "standard item 1" 0 50,
                      Item "standard item 2" 0 50
                    ]
                    expected = [
                      Item "standard item 1" -1 48,
                      Item "standard item 2" -1 48
                    ]
                  in
                    Expect.equal (updateQuality items) expected
              ],
              describe "when limit the quality is the minimum"
              [ test "returns items with less sell_in and same quality" <|
                \() ->
                  let
                    items = [
                      Item "standard item 1" 0 0,
                      Item "standard item 2" 0 0
                    ]
                    expected = [
                      Item "standard item 1" -1 0,
                      Item "standard item 2" -1 0
                    ]
                  in
                    Expect.equal (updateQuality items) expected
              ]
            ] 
          ],
        describe "Aged Brie"
          [ describe "when sell_in is up to the limit"
            [  describe "when items has no limits of quality"
              [ test "returns items with more quality and less sell_in" <|
                \() ->
                  let
                    items = [Item "Aged Brie" 11 26]
                    expected = [Item "Aged Brie" 10 27]
                  in
                    Expect.equal (updateQuality items) expected
              ],
              describe "when limit the quality is the maximum"
              [ test "returns items with same quality and less sell_in" <|
                \() ->
                  let
                    items = [Item "Aged Brie" 11 50]
                    expected = [Item "Aged Brie" 10 50]
                  in
                    Expect.equal (updateQuality items) expected
              ],
              describe "when limit the quality is the minimum"
              [ test "returns items with less sell_in and more quality" <|
                \() ->
                  let
                    items = [Item "Aged Brie" 11 0]
                    expected = [Item "Aged Brie" 10 1]
                  in
                    Expect.equal (updateQuality items) expected
              ]
            ], 
            describe "when sell_in is in the limit"
            [  describe "when items has no limits of quality"
              [ test "returns items with twice of quality and less sell_in" <|
                \() ->
                  let
                    items = [Item "Aged Brie" 0 25]
                    expected = [Item "Aged Brie" -1 27]
                  in
                    Expect.equal (updateQuality items) expected
              ],
              describe "when quality is 49"
              [ test "returns items with less sell_in and quality in the limit" <|
                \() ->
                  let
                    items = [Item "Aged Brie" 0 49]
                    expected = [Item "Aged Brie" -1 50]
                  in
                    Expect.equal (updateQuality items) expected
              ],
              describe "when limit the quality is the maximum"
              [ test "returns items with same quality and less sell_in" <|
                \() ->
                  let
                    items = [
                      Item "Aged Brie" 0 50
                    ]
                    expected = [
                      Item "Aged Brie" -1 50
                    ]
                  in
                    Expect.equal (updateQuality items) expected
              ],
              describe "when limit the quality is the minimum"
              [ test "returns items with less sell_in and same quality" <|
                \() ->
                  let
                    items = [
                      Item "Aged Brie" 0 0
                    ]
                    expected = [
                      Item "Aged Brie" -1 2
                    ]
                  in
                    Expect.equal (updateQuality items) expected
              ]
            ] 
          ],
        describe "Sulfuras"
          [ describe "when sell_in is up to the limit"
            [  describe "when items has no limits of quality"
              [ test "returns items with more quality and less sell_in" <|
                \() ->
                  let
                    items = [Item "Sulfuras" 11 26]
                    expected = [Item "Sulfuras" 11 80]
                  in
                    Expect.equal (updateQuality items) expected
              ],
              describe "when limit the quality is the maximum"
              [ test "returns items with same quality and less sell_in" <|
                \() ->
                  let
                    items = [Item "Sulfuras" 11 50]
                    expected = [Item "Sulfuras" 11 80]
                  in
                    Expect.equal (updateQuality items) expected
              ],
              describe "when limit the quality is the minimum"
              [ test "returns items with less sell_in and more quality" <|
                \() ->
                  let
                    items = [Item "Sulfuras" 11 0]
                    expected = [Item "Sulfuras" 11 80]
                  in
                    Expect.equal (updateQuality items) expected
              ]
            ], 
            describe "when sell_in is in the limit"
            [  describe "when items has no limits of quality"
              [ test "returns items with twice of quality and less sell_in" <|
                \() ->
                  let
                    items = [Item "Sulfuras" 0 25]
                    expected = [Item "Sulfuras" 0  80]
                  in
                    Expect.equal (updateQuality items) expected
              ],
              describe "when limit the quality is the maximum"
              [ test "returns items with same quality and less sell_in" <|
                \() ->
                  let
                    items = [
                      Item "Sulfuras" 0 50
                    ]
                    expected = [
                      Item "Sulfuras" 0 80
                    ]
                  in
                    Expect.equal (updateQuality items) expected
              ],
              describe "when limit the quality is the minimum"
              [ test "returns items with less sell_in and same quality" <|
                \() ->
                  let
                    items = [
                      Item "Sulfuras" 0 0
                    ]
                    expected = [
                      Item "Sulfuras" 0 80
                    ]
                  in
                    Expect.equal (updateQuality items) expected
              ]
            ] 
          ],
        describe "Backstage passes"
          [ describe "when sell_in is up to 10"
            [  describe "when items has no limits of quality"
              [ test "returns items with more quality and less sell_in" <|
                \() ->
                  let
                    items = [Item "Backstage passes" 11 26]
                    expected = [Item "Backstage passes" 10 27]
                  in
                    Expect.equal (updateQuality items) expected
              ],
              describe "when limit the quality is the maximum"
              [ test "returns items with same quality and less sell_in" <|
                \() ->
                  let
                    items = [Item "Backstage passes" 11 50]
                    expected = [Item "Backstage passes" 10 50]
                  in
                    Expect.equal (updateQuality items) expected
              ],
              describe "when limit the quality is the minimum"
              [ test "returns items with less sell_in and more quality" <|
                \() ->
                  let
                    items = [Item "Backstage passes" 11 0]
                    expected = [Item "Backstage passes" 10 1]
                  in
                    Expect.equal (updateQuality items) expected
              ]
            ], 
            describe "when sell_in is in the limit"
            [  describe "when items has no limits of quality"
              [ test "returns items with zero of quality and less sell_in" <|
                \() ->
                  let
                    items = [Item "Backstage passes" 0 25]
                    expected = [Item "Backstage passes" -1 0]
                  in
                    Expect.equal (updateQuality items) expected
              ],
              describe "when limit the quality is the maximum"
              [ test "returns items with zero of quality and less sell_in" <|
                \() ->
                  let
                    items = [
                      Item "Backstage passes" 0 50
                    ]
                    expected = [
                      Item "Backstage passes" -1 0
                    ]
                  in
                    Expect.equal (updateQuality items) expected
              ],
              describe "when limit the quality is the minimum"
              [ test "returns items with less sell_in and same quality" <|
                \() ->
                  let
                    items = [
                      Item "Backstage passes" 0 0
                    ]
                    expected = [
                      Item "Backstage passes" -1 0
                    ]
                  in
                    Expect.equal (updateQuality items) expected
              ]
            ],
            describe "when sell_in is 10"
            [  describe "when items has no limits of quality"
              [ test "returns items with twice more quality and less sell_in" <|
                \() ->
                  let
                    items = [Item "Backstage passes" 10 25]
                    expected = [Item "Backstage passes" 9 27]
                  in
                    Expect.equal (updateQuality items) expected
              ],
              describe "when limit the quality is the maximum"
              [ test "returns items with same quality and less sell_in" <|
                \() ->
                  let
                    items = [
                      Item "Backstage passes" 10 50
                    ]
                    expected = [
                      Item "Backstage passes" 9 50
                    ]
                  in
                    Expect.equal (updateQuality items) expected
              ],
              describe "when limit the quality is the minimum"
              [ test "returns items with less sell_in and twice more of quality" <|
                \() ->
                  let
                    items = [
                      Item "Backstage passes" 10 0
                    ]
                    expected = [
                      Item "Backstage passes" 9 2
                    ]
                  in
                    Expect.equal (updateQuality items) expected
              ],
              describe "when quality is 48"
              [ test "returns items with less sell_in and quality in the limit" <|
                \() ->
                  let
                    items = [Item "Backstage passes" 10 48]
                    expected = [Item "Backstage passes" 9 50]
                  in
                    Expect.equal (updateQuality items) expected
              ],
              describe "when quality is 49"
              [ test "returns items with less sell_in and quality in the limit" <|
                \() ->
                  let
                    items = [Item "Backstage passes" 10 49]
                    expected = [Item "Backstage passes" 9 50]
                  in
                    Expect.equal (updateQuality items) expected
              ]
            ],
            describe "when sell_in is 5"
            [  describe "when items has no limits of quality"
              [ test "returns items with three times more quality and less sell_in" <|
                \() ->
                  let
                    items = [Item "Backstage passes" 5 25]
                    expected = [Item "Backstage passes" 4 28]
                  in
                    Expect.equal (updateQuality items) expected
              ],
              describe "when limit the quality is the maximum"
              [ test "returns items with same quality and less sell_in" <|
                \() ->
                  let
                    items = [
                      Item "Backstage passes" 5 50
                    ]
                    expected = [
                      Item "Backstage passes" 4 50
                    ]
                  in
                    Expect.equal (updateQuality items) expected
              ],
              describe "when limit the quality is the minimum"
              [ test "returns items with less sell_in and three times more of quality" <|
                \() ->
                  let
                    items = [
                      Item "Backstage passes" 5 0
                    ]
                    expected = [
                      Item "Backstage passes" 4 3
                    ]
                  in
                    Expect.equal (updateQuality items) expected
              ],
              describe "when quality is 48"
              [ test "returns items with less sell_in and quality in the limit" <|
                \() ->
                  let
                    items = [Item "Backstage passes" 5 48]
                    expected = [Item "Backstage passes" 4 50]
                  in
                    Expect.equal (updateQuality items) expected
              ],
              describe "when quality is 49"
              [ test "returns items with less sell_in and quality in the limit" <|
                \() ->
                  let
                    items = [Item "Backstage passes" 5 49]
                    expected = [Item "Backstage passes" 4 50]
                  in
                    Expect.equal (updateQuality items) expected
              ]
            ] 
          ]
      ]
    ]
