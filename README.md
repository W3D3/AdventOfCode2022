# Advent of Code 2022 🎄

<a href = "https://adventofcode.com/2022"><img src="https://img.shields.io/badge/dynamic/json?style=flat&logo=data:image/jpg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD//gAfQ29tcHJlc3NlZCBieSBqcGVnLXJlY29tcHJlc3P/2wCEAAQEBAQEBAQEBAQGBgUGBggHBwcHCAwJCQkJCQwTDA4MDA4MExEUEA8QFBEeFxUVFx4iHRsdIiolJSo0MjRERFwBBAQEBAQEBAQEBAYGBQYGCAcHBwcIDAkJCQkJDBMMDgwMDgwTERQQDxAUER4XFRUXHiIdGx0iKiUlKjQyNEREXP/CABEIAEAAQAMBIgACEQEDEQH/xAAcAAEAAgIDAQAAAAAAAAAAAAAABggFBwMECQH/2gAIAQEAAAAAo+ZXkwwNz5LQwnkD3pk68TyBrFb2kfbiejq3H30t5vM/jFs4d0tlVAMhcWlS5tScQAf/xAAYAQADAQEAAAAAAAAAAAAAAAABBQYCBP/aAAgBAhAAAABtxDuVyOa9rAi/ysLH/8QAGAEAAwEBAAAAAAAAAAAAAAAAAQIEAAP/2gAIAQMQAAAAnbL0oM3Osxl8n//EADYQAAEEAQEEBwYEBwAAAAAAAAIBAwQFBgcACBEhEBITIDEyQRQVIlNhchYXMFJic4KRkqPB/9oACAEBAAE/AOmkpLTI7SJS0sQpM+USgyyKoimqJx9dsgx+4xa2k0d9CKJYx0BXWTVFUe0BDHmPFOaL393xvtNXcR+jr5f4sntvLN9TWDIS/fGgH/oEe7K0xz6JQV2UHi85ynmxhlNSWG+2RGj5oTiBxIEVPUujduDr6wYx9AnF/aKe29E31NWZxfMrIRdFdpjntpSzsji4xNSpiRjkuSng7ECaBOKq31+Cuf09OCbyuc4gzDrbEGLqqjgDQMv8GXgaDkgtuhtHyXd11l5XdfHqbt7z+0okKQp/R9v4HNsH3d4GB59VZjQ5G5JrWWpKLElNorqdu0oIoOhyLbUTd/haj51+KrjIHYlcMCPGWNGaTtjJpSVVVw+QptIud3LRnlXQo9tdM+VGESfJQ/5pr1G9s63m82ypmZXU7DFJWPgbRA3wffNs+Sobh9wRIiEQFVJVREROaqq7acwPyR0kfu81sZSvK37Y7EeeJRYI+TcVkC5Ca+u2ZsNa8aRpZ4XayWJRAr7cUHyBDeb88OSI7OtOsOuMvtk262SgYGnAhIV4Kiovqnd3ZdLEvLNdQb6OiVNW6qQEd8r8oPFz7GdtftWS1CyH3VUSVXGqpwhjdXyyXvApH/A20J1Yd03yT2axdIscsyBuaHyD8BkD9vgW281pczBkhqXjjYlW2Kh7yBrmAPn5JA/wO9yqZgSbSuj2sxYkByS0EmQIK4rTKkiGaCPMlFPTbWzVXHKHDKjTPTOcycN+A2MmRFPiLcMk5Ndb5jvifToRqtSWONWemOpEuP7rCE6kR+YfAFionxxzJfUPFva/j1MS7tY1DPObVNyXBiSTBQJxlF+FVRfX9L//xAAlEQACAwABAwMFAQAAAAAAAAABAgMEBQARQVEGEDESFDI0YpH/2gAIAQIBAT8A5o2Wp0LlpAC0MLuAfjqo68y7T3s6lccAPNCjkD4BI9tSTVhijky68MzBuskcjFSy/wAnzzV9V13zNGjoU7FG29eRVSVeqsxHZhzH9VVosrNoUali7bSBFZIkIVWHljzLl1Zo5JNSvDAWYGOONixC/wBHz7bfX1JqR4Fb9WuwkuzAf4gPnmMD6Y1nw7H6VpjJTlPnuhPs6l0dQxUkEdR8jmRk18isYISzu7l5JX/J2Pc81sqvr1ft5yVKsHjkX8kYdxyNTHGiFyxVQPqPyenc8//EACIRAAICAAUFAQAAAAAAAAAAAAECAAMEEBIhURETIzJBgf/aAAgBAwEBPwCIup1Xkx10O68HplWKySLCRwRK8O3cR0cMoYbiWYdjY7uyqpb7LBWCBWxPJOVXgrNzex2QS3z1i0ey7MMhLLDY3U7fAJXYam1D9EO5Jn//2Q==&labelColor=black&color=118a03&label=2022&query=2022&url=https://aoc-badge-christoph4.vercel.app/api/" target="_blank"></a>
![GitHub Build Status](https://img.shields.io/github/workflow/status/W3D3/AdventOfCode2022/Run%20Gradle%20Tests/master) ![GitHub last commit](https://img.shields.io/github/last-commit/W3D3/AdventOfCode2022)

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

| Day | Title                                                             | Solution                                           |
|-----|-------------------------------------------------------------------|----------------------------------------------------|
| 1   | 🍔 [Calorie Counting](https://adventofcode.com/2022/day/1)        | **[Day01.kt](app/src/main/kotlin/day01/Day01.kt)** |
| 2   | 🪨📄✂️ [Rock Paper Scissors](https://adventofcode.com/2022/day/2) | **[Day02.kt](app/src/main/kotlin/day02/Day02.kt)** |
| 3   | 🎒 [Rucksack Reorganization](https://adventofcode.com/2022/day/3) | **[Day03.kt](app/src/main/kotlin/day03/Day03.kt)** |
| 4   | 🧹 [Camp Cleanup](https://adventofcode.com/2022/day/4)            | **[Day04.kt](app/src/main/kotlin/day04/Day04.kt)** |
