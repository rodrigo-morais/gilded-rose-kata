module GildedRoseKataTest exposing (..)

import Test exposing (..)
import Expect

import GildedRoseKata exposing (updateQuality, Item(Item))


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
          ]
      ]
    ]
