# Reflection

## Day 01
150/611; points: 0, --th place
* `digits` was amazing, sucks I misread the question
* Had some issues with overloads, will have to look into that.
* Had issues with recording because I still had "0" bound as start stop recording.

## Day 02
265/125; points: 0, --th place
* My project was a bit too cursed, so I tried fixing it 15 minutes before the contest.
  * This was dumb; I broke it fully and had to use Python instead

## Day 03
84/125; points: 17, 275th place
* First points
* My code was slow, cause I used a list instead of a set (I wanted to use a set, but messed up)
* [x] make code use `asSet` where applicable, and make that method warn 
      if a collection is passed multiple times, but isn't a set
* Apparently the slowdown has from the fact that SimpleGrid had a hashcode and equals

## Day 04
201/103; points: 0, 324th place
* I really should run my code on the test input first
* I accidentally use python syntax instead of kotlin syntax

## Day 05
41/2; points: 159, 86th place
* LET's FUCKING GO
* [ ] Fix overload issue between stdlib and utils (min/max)

## Day 06
221/127; points: 0, 104th place

## Day 07
218/141; points: 0, 121st place

## Day 08
45/48; points: 109, 75th place

## Day 09
164/107; points: 0, 88th place

## Day 10
33/168; points: 68, 71st place

## Day 11
5/47; points: 150, 47th place
* Nice points: TOP 50
* Didn't use GitHub copilot today,
* [ ] Convert a list of BoundedGridPoints to Points easily

## Day 12
197/20; points: 81, 41st place
* Nice
* People used more bruteforce for day one to go faster but that cost
    them for part 2
* Could have used yComb
* [x] Add memoize functions
* [x] Allow yComb to be memoized easily

## Day 13
53/81; points: 78, 39th place
* A bit slow I think? 
  * Didn't sleep that well
  * I didn't do my finger stretched today, idk if that's related
* I don't think using a grid here helped me
* `find` **IS** `firstOfNull`, that's why there is no `findOrNull`

## Day 14
267/167; points: 0, 43rd place
* Didn't sleep well

## Day 15
232/172; points: 0, 50th place
* Slept better, but I don't think I was fully awake. 
    I really need to use a full hour to wake up and 
    not just lie in my bed

## Day 16
14/24; points: 164, 36th place
* Still didn't sleep well, but better morning routine might have helped
* Grids again??
* leftEdge and friends were broken
  * [x] lines of length 0 raise errors where they kinda shouldn't (maybe do add a warning?)
  * [x] bounds' top left and family were wrong

## Day 17
17/17; points: 168, 30th place
* 17, 17, 17, 17, 17, 17, 17, 17, 17, 17,
* [ ] Dijkstra result has start and end switched.

## Day 18
4/48; Points 150, 28th place
* I forgot about pick's smh, also messed up my shoelace :[
* I think using the floodfill for p1 was still the 
    better call, based on the assumption I'd need to use the wall colors

## Day 19
341/41; Points 60, 27th place
* using a map instead of a list would have avoided problems, or 
    just converting the char to an index in 1 location would have been great
* [ ] expand yComb

## Day 20
40/52; Points 150, 23rd place

## Day 21
662/9; Points 90, 24th place
* Didn't sleep well, probably why I made so many mistakes in p1
* Accidentally counted the evens in p2 instead of the odds

## Day 22
67/72; Points 63, 23rd place
* Didn't sleep well, probably why I made so many mistakes in p1 and p2

## Day 23
125/53; Points 48,
* Didn't sleep well
* [ ] yComb can have stack overflow, using coroutines would be better
* [ ] roi should be poi
* [ ] roi/poi is slow. at least make a special case for removing nodes with 2 neighbours
* [ ] longest no backtrack path? idk