port module Main exposing (..)

import GildedRoseKataTest
import Test.Runner.Node exposing (run, TestProgram)
import Json.Encode exposing (Value)


main : TestProgram
main =
    run emit GildedRoseKataTest.all


port emit : ( String, Value ) -> Cmd msg
