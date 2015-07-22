# RPG

First project ever in Java and an OO language, developed as a side project while taking the Object Oriented Programming course at ITBA in 2013.

## The Game
This game is a basic RPG with many of the features of other [Role-Playing games](https://en.wikipedia.org/wiki/Role-playing_video_game).

The player has to choose between four [classes](#classes) to go through the world. Each class has a unique set of [spells](#spells) to fight with and different starting [stats](#stats).

There are two maps, each one with regular monsters and a special Boss. When the first boss is defeated a portal appears that takes you to the second map.

On the first map, there are vendors who sell equipment that improves the characters stats.

There is also a [PvP version of the game](https://github.com/gnardini/RPG-PvP)

### Spells
All of the classes have the ability to use regular attacks, which do low damage but can be used very often (depending on the Attack Speed of the character). They are melee ranged, which means the character needs to be standing next to its objective and facing it for the attack to do damage. Some classes rely on these more than others.  
Each class also has a different set of special spells. These spells may consume Mana (depending on the class), which is a secondary resource (the primary one being Health) which regenerates over time and needs to be consumed to cast spells.  
For a spell to be able to be cast, it has to be off cooldown. Casting a spell puts it on cooldown for some time, depending on the spell. This cooldown can be reduced by improving the Cooldown Reduction stat.  

### Classes

#### Mage
The Mage is a heavy mana user. He deals a lot of damage and is ranged but is very vulnerable if an enemy manages to get close enough or he runs out of mana.
##### Skills
- **Freeze** The mage freezes nerby enemies on their spot, preventing them from moving. It doesn't stop them from attacking or casting spells.
- **FireBall** The mage throws a long ranged fire ball on a line, damaging enemies hit.
- **Blink** The mage teleports forward, going over walls or terrain if necessary.
- **Arcane Nova** The mage emits several bursts of arcane energy around him, damagin all enemies hit.

#### Warrior
The Warrior deals a lot of damage with regular attacks and has more health and defensive stats than the Mage.
##### Skills
- **Shield Bash** The warrior hits an enemy right in front of him with his shield, stunning him for a brief period of time and not letting him move, attack or cast spells for the duration.
- **Sword Thrust** The warrior thursts his sword in front of him, dealing massive damage to anyone standing in a short line in front of him. 
- **Charge** The warrior charges forward, moving very quickly in a line in front of him.
- **Quick Strikes** The warrior thrusts his sword very quickly in three different directions in a cone in front of him, dealing damage to all enemies hit.

#### Archer (Updated skills from [RPG PvP](https://github.com/gnardini/RPG-PvP))
The Archer has ranged regular attacks, which greatly help him with his damage output.
##### Skills
- **Poisonous Arrow** The archer shoots an arrow in front of him, dealing damage to the first enemy hit and poisoning him, which damages the enemy over time for a brief duration.
- **Escape** The archer jumps backwards, escaping danger. He can jump walls and terrain by using this ability.
- **Battle Rush** The archer gets inspired, incresing his Attack Speed massively for a short period of time.
- **Explosive Arrow** The archer shoots a slow projectile in front of him, dealing a great amount of damage to the first enemy hit.

#### Paladin (Only available on [RPG PvP](https://github.com/gnardini/RPG-PvP))
The Paladin does mixed physical and magic damage and his spells have moderate range.
##### Skills
- **Desecration** The paladin blesses the ground beneath him and a small radius around, doing damage to enemies and reducing their defenses for some time.
- **Judgment** The paladin instantly hits all enemies in a straight line in front of him with a ray of bright light, damaging and stunning them, which prevents them from moving, attacking or casting spells.
- **Rejuvenation** The paladin reaches an enlightened state, healing himself over time.
- **Holy Nova** The paladin launches a wave of light in a small radius around him, damaging all enemies hit.

### Stats

- **Physiscal Damage** Regular attacks deal damage equal to this stat and warrior and archer stats deal damage proportional to it (with different ratios, depending on the spell).
- **Magical Damage** Mage spells scale with the amount of this stat he gets.
- **Armor** Reduces the amount of physical damage taken.
- **Magic Resistance** Reduces the amount of magical damage taken.
- **Max HP** Maximum amount of Health.
- **Max Mana** Maximum amount of mana.
- **HP Regeneration** Amount of Healthrecovered per second.
- **Mana Regeneration** Amount of Mana recovered per second.
- **Attack Speed** Maximum amount of regular attacks per second.
- **Cooldown Reduction** Percentage of reduction on the time required to wait until a spell can be cast again.

### Controls
- **Move Up** Up Arrow
- **Move Down** Down Arrow
- **Move Left** Left Arrow
- **Move Right** Right Arrow
- **Regular Attack** 'A'
- **Spell 1** 'Q'
- **Spell 2** 'W'
- **Spell 3** 'E'
- **Spell 4** 'R'
- **See Stats** 'C'
- **Interact with vendor** 'P'
- **Use inventory item** Numbers

### Screenshots

![fighting](https://cloud.githubusercontent.com/assets/6062888/8840064/bce2b55e-30b2-11e5-86e5-9bac2580b842.png)

![character info](https://cloud.githubusercontent.com/assets/6062888/8840072/ce3d9666-30b2-11e5-9a46-a5fd7cf9cc00.png)

![portal](https://cloud.githubusercontent.com/assets/6062888/8840075/d7ad3558-30b2-11e5-8b2f-b9e761553925.png)

![death](https://cloud.githubusercontent.com/assets/6062888/8840077/db6f6fe4-30b2-11e5-8620-e838153576e2.png)

![store](https://cloud.githubusercontent.com/assets/6062888/8840079/ddd1e58c-30b2-11e5-9915-fc2c26ce396d.png)

![store2inv](https://cloud.githubusercontent.com/assets/6062888/8840080/e096c472-30b2-11e5-951b-afc38a090b9f.png)