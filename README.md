# OVERSTORY
Ryan Jacobs, Justin Carter, Ryan King, Chris DeLaGarza

(Read Me instructions 1 (#) at the begining of a line followed by space for Small Heading 2(#) for large font heading. Keep Weekly Progress in Read Me)

# Ryan Jacobs
## Week 1
focused on learning the libgdx library, created an animation function that takes in a spritesheet and converts it to an animation, changed character models, attempted to implement states
## Week 2
Began to add more enemies into the game, and created functions to determine their HP, XP, and DMG based on the enemy's current level. Added a way to set which enemies spawn into which wave, and going to add wave randomization.
## Week 3
Worked on determining a system for which enemies spawn in per wave. Changed XP, Health, and Damage scaling per enemy. Created animation function, and began to add in player animations.
## Week 4
Began work on campaign maps
https://github.com/libgdx/libgdx/wiki/Tile-maps
## Week 5 
Continued work on map creation and various bug fixes
## Week 6
Continued work on map creation and various bug fixes
## Week 7
Implemented a music director that can play music and sounds
## Week 8
Began to balance various enemies and weapons
## Week 9
Continued to balance, and began to plan for implementation of features allowing for a campaign(dialog, weapon drops, quests)
# Justin Carter
## Week 1
Art

# Ryan King
## Week 1
Created basic enemy movement, then upgraded that movement to track the player, created a basic pixel art item for testing, started painful process of trying to merge experimental tilemaps and master, set up README, and created enemy/player collision detection. 
## Week 2
Added more enemy travel directions to smoothen movement, started working on the long process of creating a pathfinding system for the enemies (attempted Dijkstra's algorithm and started working on a method called AStar).
## Mr. Hudson's Comment
https://github.com/libgdx/gdx-ai/wiki/Pathfinding
LibGDX has a pathfinding system built in based on the tilemaps. You would need to change the sprite associated with each character to 32x32 to fit the map. But the advantage is you simply draw the character at an offset with the animation sprites constantly.
## Week 3
Began working finalizing AStar grid (made it a 64x64 block grid, found player/enemy location, etc) then dropped it to use the LibGDX AStar methods. Worked on fixing missing files in my project and reversing a merge that got committed to master by accident.
## Week 4
Restarted AStar pathfinding in order to use LibGDX's very undocumented system. Watched a tutorial and scoured the Internet for help, which is almost entirely outdated or nonexistent. Started researching nodes and dissecting the IndexedAStar Class because it looks like I'm entirely on my own for this. Not fun.
## Week 5
Finally finished AStar Pathfinding, which works for both enemy types (although the larger ones will clip things at the moment). Working on smoothing things out so transitions look better. 

# Chris DeLaGarza
## Week 1
Created the tilemap, the player, the enemies. Added health, xp, leveling, and enemy waves system. Added health bar for enemies that works. Added collision, and a way to spawn enemies around the edge of the map at a random location. Enemies can be killed. Enemies drop xp.
## Week 2
Added more items and weapons. Also added a health bar for the player, and a backpack that functions correctly, as well as hot bars for items that can be swapped out with items in the backpack.
## Week 3
Worked on projectiles more, added proper explosions and shuriken that shoots 3 projectiles each time. Also fixed memory leakage to a great extent.
## Week 4 - the rest
The whole game...
