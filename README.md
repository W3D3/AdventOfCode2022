# Advent of Code 2022 🎄

<a href = "https://adventofcode.com/2022"><img src="https://img.shields.io/badge/dynamic/json?style=flat&logo=data:image/jpg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD//gAfQ29tcHJlc3NlZCBieSBqcGVnLXJlY29tcHJlc3P/2wCEAAQEBAQEBAQEBAQGBgUGBggHBwcHCAwJCQkJCQwTDA4MDA4MExEUEA8QFBEeFxUVFx4iHRsdIiolJSo0MjRERFwBBAQEBAQEBAQEBAYGBQYGCAcHBwcIDAkJCQkJDBMMDgwMDgwTERQQDxAUER4XFRUXHiIdGx0iKiUlKjQyNEREXP/CABEIAEAAQAMBIgACEQEDEQH/xAAcAAEAAgIDAQAAAAAAAAAAAAAABggFBwMECQH/2gAIAQEAAAAAo+ZXkwwNz5LQwnkD3pk68TyBrFb2kfbiejq3H30t5vM/jFs4d0tlVAMhcWlS5tScQAf/xAAYAQADAQEAAAAAAAAAAAAAAAABBQYCBP/aAAgBAhAAAABtxDuVyOa9rAi/ysLH/8QAGAEAAwEBAAAAAAAAAAAAAAAAAQIEAAP/2gAIAQMQAAAAnbL0oM3Osxl8n//EADYQAAEEAQEEBwYEBwAAAAAAAAIBAwQFBgcACBEhEBITIDEyQRQVIlNhchYXMFJic4KRkqPB/9oACAEBAAE/AOmkpLTI7SJS0sQpM+USgyyKoimqJx9dsgx+4xa2k0d9CKJYx0BXWTVFUe0BDHmPFOaL393xvtNXcR+jr5f4sntvLN9TWDIS/fGgH/oEe7K0xz6JQV2UHi85ynmxhlNSWG+2RGj5oTiBxIEVPUujduDr6wYx9AnF/aKe29E31NWZxfMrIRdFdpjntpSzsji4xNSpiRjkuSng7ECaBOKq31+Cuf09OCbyuc4gzDrbEGLqqjgDQMv8GXgaDkgtuhtHyXd11l5XdfHqbt7z+0okKQp/R9v4HNsH3d4GB59VZjQ5G5JrWWpKLElNorqdu0oIoOhyLbUTd/haj51+KrjIHYlcMCPGWNGaTtjJpSVVVw+QptIud3LRnlXQo9tdM+VGESfJQ/5pr1G9s63m82ypmZXU7DFJWPgbRA3wffNs+Sobh9wRIiEQFVJVREROaqq7acwPyR0kfu81sZSvK37Y7EeeJRYI+TcVkC5Ca+u2ZsNa8aRpZ4XayWJRAr7cUHyBDeb88OSI7OtOsOuMvtk262SgYGnAhIV4Kiovqnd3ZdLEvLNdQb6OiVNW6qQEd8r8oPFz7GdtftWS1CyH3VUSVXGqpwhjdXyyXvApH/A20J1Yd03yT2axdIscsyBuaHyD8BkD9vgW281pczBkhqXjjYlW2Kh7yBrmAPn5JA/wO9yqZgSbSuj2sxYkByS0EmQIK4rTKkiGaCPMlFPTbWzVXHKHDKjTPTOcycN+A2MmRFPiLcMk5Ndb5jvifToRqtSWONWemOpEuP7rCE6kR+YfAFionxxzJfUPFva/j1MS7tY1DPObVNyXBiSTBQJxlF+FVRfX9L//xAAlEQACAwABAwMFAQAAAAAAAAABAgMEBQARQVEGEDESFDI0YpH/2gAIAQIBAT8A5o2Wp0LlpAC0MLuAfjqo68y7T3s6lccAPNCjkD4BI9tSTVhijky68MzBuskcjFSy/wAnzzV9V13zNGjoU7FG29eRVSVeqsxHZhzH9VVosrNoUali7bSBFZIkIVWHljzLl1Zo5JNSvDAWYGOONixC/wBHz7bfX1JqR4Fb9WuwkuzAf4gPnmMD6Y1nw7H6VpjJTlPnuhPs6l0dQxUkEdR8jmRk18isYISzu7l5JX/J2Pc81sqvr1ft5yVKsHjkX8kYdxyNTHGiFyxVQPqPyenc8//EACIRAAICAAUFAQAAAAAAAAAAAAECAAMEEBIhURETIzJBgf/aAAgBAwEBPwCIup1Xkx10O68HplWKySLCRwRK8O3cR0cMoYbiWYdjY7uyqpb7LBWCBWxPJOVXgrNzex2QS3z1i0ey7MMhLLDY3U7fAJXYam1D9EO5Jn//2Q==&labelColor=black&color=118a03&label=2022&query=2022&url=https://aoc-badge-christoph4.vercel.app/api/" target="_blank"></a>
![GitHub Build Status](https://img.shields.io/github/actions/workflow/status/W3D3/AdventOfCode2022/gradle.yml?branch=master) ![GitHub last commit](https://img.shields.io/github/last-commit/W3D3/AdventOfCode2022)

Solutions for the [AoC 2022](https://adventofcode.com/2022) puzzles, written in Kotlin.

## Running

To run any specific day, with your puzzle input, simply run:

```shell
./gradlew run -Dday={day number} -DsessionCookie={session cookie}
```

where `{day number}` is the day and `{session cookie}` is your AoC session cookie value.

To run all the solutions with the example test data, run:

```shell
./gradlew test
```

## Solutions

| Day | Title                                                               | Solution                                           |
|-----|---------------------------------------------------------------------|----------------------------------------------------|
| 1   | 🍔 [Calorie Counting](https://adventofcode.com/2022/day/1)          | **[Day01.kt](app/src/main/kotlin/day01/Day01.kt)** |
| 2   | 🪨📄✂️ [Rock Paper Scissors](https://adventofcode.com/2022/day/2)   | **[Day02.kt](app/src/main/kotlin/day02/Day02.kt)** |
| 3   | 🎒 [Rucksack Reorganization](https://adventofcode.com/2022/day/3)   | **[Day03.kt](app/src/main/kotlin/day03/Day03.kt)** |
| 4   | 🧹 [Camp Cleanup](https://adventofcode.com/2022/day/4)              | **[Day04.kt](app/src/main/kotlin/day04/Day04.kt)** |
| 5   | 📦 [Supply Stacks](https://adventofcode.com/2022/day/5)             | **[Day05.kt](app/src/main/kotlin/day05/Day05.kt)** |
| 6   | 📡 [Tuning Trouble](https://adventofcode.com/2022/day/6)            | **[Day06.kt](app/src/main/kotlin/day06/Day06.kt)** |
| 7   | 📟 [No Space Left On Device](https://adventofcode.com/2022/day/7)   | **[Day07.kt](app/src/main/kotlin/day07/Day07.kt)** |
| 8   | 🌳 [Treetop Tree House](https://adventofcode.com/2022/day/8)        | **[Day08.kt](app/src/main/kotlin/day08/Day08.kt)** |
| 9   | 🪢 [Rope Bridge](https://adventofcode.com/2022/day/9)               | **[Day09.kt](app/src/main/kotlin/day09/Day09.kt)** |
| 10  | 📺 [Cathode-Ray Tube](https://adventofcode.com/2022/day/10)         | **[Day10.kt](app/src/main/kotlin/day10/Day10.kt)** |
| 11  | 🐒 [Monkey in the Middle](https://adventofcode.com/2022/day/11)     | **[Day11.kt](app/src/main/kotlin/day11/Day11.kt)** |
| 12  | ⛰️ [Hill Climbing Algorithm](https://adventofcode.com/2022/day/12)  | **[Day12.kt](app/src/main/kotlin/day12/Day12.kt)** |
| 13  | 📦 [Distress Signal](https://adventofcode.com/2022/day/13)          | **[Day13.kt](app/src/main/kotlin/day13/Day13.kt)** |
| 14  | ⏳️ [Regolith Reservoir](https://adventofcode.com/2022/day/14)       | **[Day14.kt](app/src/main/kotlin/day14/Day14.kt)** |
| 15  | 📡 [Beacon Exclusion Zone](https://adventofcode.com/2022/day/15)    | **[Day15.kt](app/src/main/kotlin/day15/Day15.kt)** |
| 16  | 🌋 [Proboscidea Volcanium](https://adventofcode.com/2022/day/16)    | WIP                                                |
| 17  | 🪨 [Pyroclastic Flow](https://adventofcode.com/2022/day/17)         | **[Day17.kt](app/src/main/kotlin/day17/Day17.kt)** |
| 18  | 🫖 [Boiling Boulders](https://adventofcode.com/2022/day/18)         | **[Day18.kt](app/src/main/kotlin/day18/Day18.kt)** |
| 19  | 🤖 [Not Enough Minerals](https://adventofcode.com/2022/day/19)      | WIP                                                |
| 20  | 🌳 [Grove Positioning System](https://adventofcode.com/2022/day/20) | WIP                                                |
| 20  | 🔢 [Monkey Math](https://adventofcode.com/2022/day/21)              | **[Day21.kt](app/src/main/kotlin/day21/Day21.kt)** |