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

# Justin Carter
## Week 1
Art

# Ryan King
## Week 1
Created basic enemy movement, then upgraded that movement to track the player, created a basic pixel art item for testing, started painful process of trying to merge experimental tilemaps and master, set up README, and created enemy/player collision detection. 
## Week 2
Added more enemy travel directions to smoothen movement, started working on the long process of creating a pathfinding system for the enemies (attempted Dijkstra's algorithm and started working on a method called AStar).
https://github.com/libgdx/gdx-ai/wiki/Pathfinding
LibGDX has a pathfinding system built in based on the tilemaps. You would need to change the sprite associated with each character to 32x32 to fit the map. But the advantage is you simply draw the character at an offset with the animation sprites constantly. 
This may help looks like a good rescource       

# Chris DeLaGarza
## Week 1
Created the tilemap, the player, the enemies. Added health, xp, leveling, and enemy waves system. Added health bar for enemies that works. Added collision, and a way to spawn enemies around the edge of the map at a random location. Enemies can be killed. Enemies drop xp.
