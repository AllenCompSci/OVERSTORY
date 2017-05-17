package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.Item;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons.*;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

import javax.xml.soap.Text;

import java.io.IOException;
import java.util.Random;

import static onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play.MonsterType.*;

/**
 * Created by chris on 2/8/2017.
 */
public class spriteTextures {
    public enum RUNE{AVALANCHE, EXPLOSION, GREATFIREBALL, HEAVYMAGICMISSILE, HOLYMISSILE, ICICLE, MAGICWALL, STONESHOWER, SUDDENDEATH, THUNDERSTORM, WILDGROWTH};

    public static Texture ExitButton = new Texture("ExitButton.png");
    public static Texture ResumeButton = new Texture("resume_button.png");
    public static Texture PauseScreenBackground = new Texture("PauseScreen.png");

    public static RUNE getRUNETYPE(){
        switch((int)(Math.random()*11)){
            case 0:
                return RUNE.AVALANCHE;
            case 1:
                return RUNE.EXPLOSION;
            case 2:
                return RUNE.GREATFIREBALL;
            case 3:
                return RUNE.HEAVYMAGICMISSILE;
            case 4:
                return RUNE.HOLYMISSILE;
            case 5:
                return RUNE.ICICLE;
            case 6:
                return RUNE.MAGICWALL;
            case 7:
                return RUNE.STONESHOWER;
            case 8:
                return RUNE.SUDDENDEATH;
            case 9:
                return RUNE.WILDGROWTH;
        }
        return RUNE.THUNDERSTORM;
    }

    public static Texture ava = new Texture("RUNE/Avalanche.png");
    public static Texture exp = new Texture("RUNE/Explosion.png");
    public static Texture gfb = new Texture("RUNE/Great_Fireball.png");
    public static Texture hmm = new Texture("RUNE/Heavy_Magic_Missile.png");
    public static Texture hm = new Texture("RUNE/Holy_Missile.png");
    public static Texture icl = new Texture("RUNE/Icicle.png");
    public static Texture mw = new Texture("RUNE/Magic_Wall.png");
    public static Texture ss = new Texture("RUNE/Stone_Shower.png");
    public static Texture sd = new Texture("RUNE/Sudden_Death.png");
    public static Texture ts = new Texture("RUNE/Thunderstorm.png");
    public static Texture wg = new Texture("RUNE/Wild_Growth.png");
    public static Texture circl = new Texture("FX/CIRCLE(1x4).png");
    public static Texture wildgrowth = new Texture("FX/WildGrowth(1x3).png");
    public static Texture runeSprite(RUNE rune){
        switch(rune){
            case AVALANCHE:
                return ava;
            case EXPLOSION:
                return exp;
            case GREATFIREBALL:
                return gfb;
            case HEAVYMAGICMISSILE:
                return hmm;
            case HOLYMISSILE:
                return hm;
            case ICICLE:
                return icl;
            case MAGICWALL:
                return mw;
            case STONESHOWER:
                return ss;
            case SUDDENDEATH:
                return sd;
            case WILDGROWTH:
                return wg;
        }
        return ts;
    }

    public static Animation<TextureRegion> RuneFX(RUNE rune){
        switch(rune){
            case ICICLE:
            case AVALANCHE:
                return Play.fourFrameAnimationCreator(icicle, 1, 3, .2f);
            case EXPLOSION:
                return Play.fourFrameAnimationCreator(physical, 1,3, .2f);
            case GREATFIREBALL:
                return Play.fourFrameAnimationCreator(explosion, 1,8, .2f);
            case HOLYMISSILE:
                return Play.fourFrameAnimationCreator(holy, 2, 4, .2f);
            case MAGICWALL:
                return Play.fourFrameAnimationCreator(magicWall, 1, 9, .2f);
            case WILDGROWTH:
                return Play.fourFrameAnimationCreator(circl,1,4,.2f);
            case STONESHOWER:
                return Play.fourFrameAnimationCreator(stone,1,4,.2f);
            case SUDDENDEATH:
                return Play.fourFrameAnimationCreator(death, 1,8,.2f);
        }
        return Play.fourFrameAnimationCreator(electric, 1,6,.01f);
    }

    public static boolean wide[][] =
    {
        {false, false, true, true, true, false, false},
        {false,  true, true, true, true,  true, false},
        {true,   true, true, true, true,  true, true },
        {true,   true, true, false, true,  true, true },
        {true,   true, true, true, true,  true, true },
        {false,  true, true, true, true,  true, false},
        {false, false, true, true, true, false, false},

    };
    public static boolean cross[][] =
            {
                    {false, true, false},
                    { true, false, true},
                    {false, true, false}
            };
    public static boolean target[][] =
            {
                    {true}
            };
    public static boolean[][] getARRAY(RUNE rune){
            switch(rune){
                case AVALANCHE:
                case GREATFIREBALL:
                case THUNDERSTORM:
                case STONESHOWER:
                    return wide;
                case EXPLOSION:
                case HOLYMISSILE:
                    return cross;
            }
            return target;
    }

    public static Texture HealthPotionSprite = new Texture("HealthPotion.png");
    public static Texture RegenPotionSprite = new Texture("RegenPotion.png");
    public static Texture DonaldTrump = new Texture("32 pix/DonaldTrump.png");
    public static Texture MiniGunSprite = new Texture("MiniGun.png");
    public static Texture LoadingScreenSprite = new Texture("LoadingScreen.jpeg");
    public static Texture swordProjectileSprite = new Texture("swordProjectile.png");
    public static Texture MainMenuButtonSprite = new Texture("MainMenuButton.png");
    public static Texture NewGameButtonSprite = new Texture("ButtonNewGame.png");
    public static Texture MainMenuBackground = new Texture("MainMenuBackground.png");
    public static Texture spellbook = new Texture("Spellbook.png");
    public static Texture playButtonHoverOver = new Texture("play_button_hoverover.png");
    public static Texture playButton = new Texture("play_button.png");
    public static Texture WizardOrbProjectileSprite = new Texture("Frozen_Starlight.png");
    public static Texture LandMineProjectileSprite = new Texture("LandMine.png");
    public static Texture ArrowProjectileSprite = new Texture("arrow.png");
    public static Texture WizardStaffSprite = new Texture("staff.png");
    public static Texture SwordItemSprite = new Texture("sword.png");
    public static Texture ShurikenItemSprite = new Texture("shuriken.png");
    public static Texture LaserGunSprite = new Texture("laserGun.png");
    public static Texture LaserBulletSprite = new Texture("LaserBullet.png");
    public static Texture NullProjectileItemSprite = new Texture("nullItem.png");
    public static Texture MagicItemSprite = new Texture("SPELL.png");
    public static Texture LightningStaffSprite = new Texture("LightningStaff.png");
    public static Texture FistSprite = new Texture("fists.png");
    public static Texture BowSprite = new Texture("Bow.png");
    public static Texture LandMineItemSprite = new Texture("LandMineItem.png");
    public static Texture guiRefreshBox = new Texture("refreshBox.png");
    public static Texture explosionTexture = new Texture("explosion.png");
    public static Texture explosionSpritesTexture = new Texture("explosionSprites.png");
    public static Texture shurikenProjectileTexture = new Texture("shurikenProjectile.png");
    public static Texture ashTexture = new Texture("still1.png");
    public static Texture goblinTexture = new Texture("thor32.png");
    public static Texture bruteTexture = new Texture("Brute.png");
    public static Texture demonTexture = new Texture("demon(64x64)(4col2row)(256x128).png");
    public static Texture dragonTexture = new Texture("redDragon(64x64)(4col2row)(256x128).png");
    public static Texture hydraTexture = new Texture("hydra 2col1row.png");
    public static Texture healthBar = new Texture("greenbar.png");
    public static Texture lostHealthBar = new Texture("redbar.png");
    public static Texture LightningOrbSprites = new Texture("LightningOrb.png");
    public static Texture LightningOrb = new Texture("LightningOrbArea.png");
    public static Texture invisProjectileSprite = new Texture("invisArea.png");
    public static Texture TurretItemSprite = new Texture("TurretItem.png");
    public static Texture TurretOffSprite = new Texture("TurretOff.png");
    public static Texture TurretOnSprite = new Texture("TurretOn.png");
    public static Texture magic = new Texture("MAGIC(3x8).png");
    public static Texture holy = new Texture("holy(2x4).png");
    public static Texture ice = new Texture("ice(2x4).png");
    public static Texture earth = new Texture("earth(2x4).png");
    public static Texture death = new Texture("FX/Death(1x8).png");
    public static Texture WizardOrbAnimationTexture = new Texture("mage-E-ani.png");
    public static Texture blast = new Texture ("FX/Blast(1x5).png");
    public static Texture blueelectric = new Texture("FX/Blueelectric(1x6).png");
    public static Texture electric = new Texture("FX/electric(1x6).png");
    public static Texture stormRAGE = new Texture("FX/Storm.png");//1x6
    public static Texture yelllowelectric = new Texture("FX/Yellowelectric(1x6).png");
    public static Texture storm = new Texture("FX/storm(1x6).png");
    public static Texture goldenergy = new Texture("FX/Goldelectric(1x6).png");
    public static Texture greenelectric = new Texture("FX/Greenelectric(1x6).png");
    public static Texture explosion = new Texture ("FX/Explosion(1x8).png");
    public static Texture fire = new Texture("FX/Fire(1x8).png");
    public static Texture whitedeath = new Texture("FX/WhiteDeath(1x8).png");
    public static Texture energy = new Texture("FX/Energy(1x8).png");
    public static Texture icicle = new Texture("FX/ICE(1x3).png");
    public static Texture stone = new Texture("FX/STONE(1x4).png");
    public static Texture physical = new Texture("FX/PHYl(1x3).png");
    public static Texture magicWall = new Texture("FX/MagicWallCreation(1x9).png");
    public static Texture magicwall = new Texture("FX/MagicWall(1x3).png");

    public static Animation<TextureRegion> FX(int count1){
        switch(count1)
        {
            case 0:
             return Play.fourFrameAnimationCreator(holy, 2, 4, .2f);
            case 1:
                return Play.fourFrameAnimationCreator(ice, 2, 4, .2f);
            case 2:
                return Play.fourFrameAnimationCreator(earth, 2, 4, .2f);
            case 3:
                return Play.fourFrameAnimationCreator(death, 1,8,.2f);
            case 4:
                return Play.fourFrameAnimationCreator(greenelectric, 1,6,.2f);
            case 5:
                return Play.fourFrameAnimationCreator(greenelectric, 1,6,.2f);
            case 6:
                return Play.fourFrameAnimationCreator(blast, 1,5,.2f);
            case 7:
                return Play.fourFrameAnimationCreator(goldenergy, 1,6,.2f);
            case 8:
                return Play.fourFrameAnimationCreator(fire, 1,8,.2f);
            case 9:
                return Play.fourFrameAnimationCreator(electric, 1,6,.2f);
            case 10:
                return Play.fourFrameAnimationCreator(blueelectric, 1,6,.2f);
            case 11:
                return Play.fourFrameAnimationCreator(yelllowelectric, 1,6,.2f);
            case 12:
                return Play.fourFrameAnimationCreator(storm, 1,6,.2f);
            case 13:
                return Play.fourFrameAnimationCreator(stormRAGE, 1,6,.2f);
            case 14:
                return Play.fourFrameAnimationCreator(whitedeath, 1, 8, .2f);
            case 15:
                return Play.fourFrameAnimationCreator(explosion, 1,8, .2f);
            case 16:
                return Play.fourFrameAnimationCreator(energy, 1,8,.2f);
        }
        return Play.fourFrameAnimationCreator(magic, 3,  8, .01f);

    }
    public static Texture basic32 = new Texture("32 pix/32x32x.png");
    public static Texture brittleSkelly = new Texture("32 pix/BrittleSkeleton(2x4).png");
    public static Texture bunny = new Texture("32 pix/Bunny(1x2).png");
    public static Texture demonSkelly = new Texture("32 pix/DemonSkeleton(1x2).png");
    public static Texture eliteOrc = new Texture("32 pix/EliteOrc(1x2).png");
    public static Texture ghost = new Texture("32 pix/Ghost(1x2).png");
    public static Texture gozz = new Texture("32 pix/Gozzler(1x2).png");
    public static Texture orc = new Texture("32 pix/Orc(2x4).png");
    public static Texture orcLeader = new Texture("32 pix/OrcLeader(1x2).png");
    public static Texture pirateBcaneer = new Texture("32 pix/PirateBucaneer(1x2).png");
    public static Texture pirateCut = new Texture("32 pix/PirateCutthroat(1x2).png");
    public static Texture priestess = new Texture("32 pix/Priestess(1x2).png");
    public static Texture rithFrag = new Texture("32 pix/riftFrag(2x3).png");
    public static Texture puddle = new Texture("32 pix/slickPuddle(1x3).png");
    public static Texture squirrel = new Texture("32 pix/squirel(1x2).png");
    public static Texture vamp = new Texture("32 pix/Vampire(1x2).png");
    public static Texture warlock = new Texture("32 pix/Warlock(1x2).png");
    public static Texture zab = new Texture("32 pix/zabWiz(1x2).png");
    public static Texture acolyte = new Texture("32 pix/Acolyte(1x2).png");
    public static Texture bloodfist = new Texture("32 pix/Blood_Fist(1x2).png");
    public static Texture bloodhand = new Texture("32 pix/Blood_Hand(1x2).png");
    public static Texture bloomofdoom = new Texture("32 pix/Bloom_of_Doom(1x2).png");
    public static Texture darkapprentice = new Texture("32 pix/Dark_Apprentice(1x2).png");
    public static Texture dawnasura = new Texture("32 pix/Dawn_Asura(2x4).png");
    public static Texture deathslicer = new Texture("32 pix/Deathslicer(1x3).png");
    public static Texture doctor = new Texture("32 pix/Doctor(1x2).png");
    public static Texture doomsday = new Texture("32 pix/Doomsday_Cultist(1x2).png");
    public static Texture enragedghost = new Texture("32 pix/Enraged_Ghost(1x2).png");
    public static Texture eyeservant = new Texture("32 pix/Eye(1x2).png");
    public static Texture firedevit= new Texture("32 pix/Fire_Devil(1x2).png");
    public static Texture necromancer= new Texture("32 pix/Necromancer(1x2).png");
    public static Texture orcberserker= new Texture("32 pix/Orc_Berserker(1x2).png");
    public static Texture orcwarlord= new Texture("32 pix/Orc_Warlord(1x2).png");
    public static Texture orcwarrior= new Texture("32 pix/Orc_Warrior(1x2).png");
    public static Texture shadowpupil= new Texture("32 pix/Shadow_Pupil(1x2).png");
    public static Texture goblinbasic = new Texture("32 pix/Goblin_Basic(2x4).png");
    public static Texture midnightasura = new Texture("32 pix/Midnight_Asura(2x4).png");
    public static Texture orcshaman = new Texture("32 pix/Orc_Shaman(2x4).png");
    public static Texture orcspearman = new Texture("32 pix/Orc_Spearman(2x4).png");
    public static Texture banshee = new Texture("32 pix/Banshee.png");
    public static Texture mummy = new Texture("32 pix/Mummy.png");

    public static Texture basic64 = new Texture("64 pix/64x64.png");
    public static Texture ambassador = new Texture("64 pix/Ambassador(1x2).png");
    public static Texture ancientScar = new Texture("64 pix/AncientScarab(1x2).png");
    public static Texture barbAx = new Texture("64 pix/BarbAxe(1x2).png");
    public static Texture betrayer = new Texture("64 pix/BetrayedWraith(1x2).png");
    public static Texture bogRaider = new Texture("64 pix/BogRaider(1x2).png");
    public static Texture boneBeast = new Texture("64 pix/Bonebeast(1x2).png");
    public static Texture botHat = new Texture("64 pix/BothiamHat(1x2).png");
    public static Texture carniphila = new Texture("64 pix/Carniphila(2x4).png");
    public static Texture caveBear = new Texture("64 pix/CaveBear(2x4).png");
    public static Texture cliffstrider = new Texture("64 pix/CliffStrider(1x2).png");
    public static Texture corlerianbarb = new Texture("64 pix/CorlerianBarb(1x2).png");
    public static Texture crawler = new Texture("64 pix/Crawler(1x2).png");
    public static Texture crushedWalker = new Texture("64 pix/CrushedWalker(1x2).png");
    public static Texture crystalWolf = new Texture("64 pix/CrystalWolf(2x4).png");
    public static Texture darkstalker = new Texture("64 pix/DarkStalker(1x2).png");
    public static Texture deathStrike = new Texture("64 pix/DeathStrike(1x2).png");
    public static Texture deepTerror = new Texture("64 pix/DeepTerror(2x4).png");
    public static Texture destroyer = new Texture("64 pix/Destroyer(1x2).png");
    public static Texture devorga = new Texture("64 pix/Devovorga(1x2).png");
    public static Texture diabloicImp = new Texture("64 pix/DiabloicImp(1x2).png");
    public static Texture earthElemental = new Texture("64 pix/EarthElemental(1x2).png");
    public static Texture empoweredGlooth = new Texture("64 pix/EmpowerdGlooth(2x4).png");
    public static Texture energyEle1 = new Texture("64 pix/EnergyElemental(1x4).png");
    public static Texture energyEle2 = new Texture("64 pix/EnergyElemental(1x4).png");
    public static Texture energyScorp = new Texture("64 pix/EnergyScorpion(2x4).png");
    public static Texture enragedCrystalGolem = new Texture("64 pix/EnragedCrystalGolem(1x2).png");
    public static Texture enragedGolem = new Texture("64 pix/EnragedGolem(1x2).png");
    public static Texture eternalGuard = new Texture("64 pix/EternalGuard(1x2).png");
    public static Texture ferm = new Texture("64 pix/Ferm(1x2).png");
    public static Texture fireElemental = new Texture("64 pix/FireElemental(1x2).png");
    public static Texture gargoyle = new Texture("64 pix/Gargoyle(1x2).png");
    public static Texture ghostFerm = new Texture("64 pix/GhostFerm(2x4).png");
    public static Texture gloombringer = new Texture("64 pix/GloomBringer(1x2).png");
    public static Texture gloothMasher = new Texture("64 pix/GoolthMasher(1x2).png");
    public static Texture gorgon = new Texture("64 pix/Gorgon(1x2).png");
    public static Texture grim = new Texture("64 pix/Grim(1x2).png");
    public static Texture grimLeech = new Texture("64 pix/Grimleech(2x4).png");
    public static Texture hellflayer = new Texture("64 pix/Hellflayer(2x4).png");
    public static Texture hellhound = new Texture("64 pix/Hellhound(1x2).png");
    public static Texture hiveOverseer = new Texture("64 pix/HiveOverseer(1x2).png");
    public static Texture infectedWeeper = new Texture("64 pix/InfectedWeeper(1x2).png");
    public static Texture infernalist = new Texture("64 pix/Infernalist(1x2).png");
    public static Texture jackolatern = new Texture("64 pix/JackOLantern(1x4).png");
    public static Texture juggernaut = new Texture("64 pix/Juggernaut(1x2).png");
    public static Texture ladybug = new Texture("64 pix/LadyBug(2x4).png");
    public static Texture lizardChosen = new Texture("64 pix/LizardChosen(1x2).png");
    public static Texture lizardHighGuard = new Texture("64 pix/LizardHighGuard(1x2).png");
    public static Texture lizardLegionaire = new Texture("64 pix/LizardLegionaire(1x2).png");
    public static Texture lizardPriest = new Texture("64 pix/LizardPriest(1x2).png");
    public static Texture lizardZaogun = new Texture("64 pix/LizardZaogun(1x2).png");
    public static Texture lostSoul = new Texture("64 pix/LostSoul(1x2).png");
    public static Texture madMage = new Texture("64 pix/MadMage(1x2).png");
    public static Texture massFireElemental = new Texture("64 pix/MassiveFireElemental(1x2).png");
    public static Texture medusa = new Texture("64 pix/Medusa(1x2).png");
    public static Texture midnightPanther = new Texture("64 pix/MidnightPanther(2x4).png");
    public static Texture nightmaregaz = new Texture("64 pix/NightmareGazharagot(1x2).png");
    public static Texture nobelLion = new Texture("64 pix/NobelLion(2x4).png");
    public static Texture orcMaurader = new Texture("64 pix/OrcMaurader(1x2).png");
    public static Texture orcRider = new Texture("64 pix/OrcRider(1x2).png");
    public static Texture oreWalker = new Texture("64 pix/OreWalker(1x2).png");
    public static Texture phodomo = new Texture("64 pix/Phrodomo(1x2).png");
    public static Texture pinataDragon = new Texture("64 pix/PinataDragon(2x4).png");
    public static Texture pirateCorsair = new Texture("64 pix/PirateCorsair(1x2).png");
    public static Texture pirateGhost = new Texture("64 pix/PirateGhost(1x2).png");
    public static Texture pirateMarauder = new Texture("64 pix/PirateMarauder(1x2).png");
    public static Texture pitBat1 = new Texture("64 pix/PitBattler1(2x4).png");
    public static Texture pitBat2 = new Texture("64 pix/PitBattler2(2x4).png");
    public static Texture plagueSmith = new Texture("64 pix/PlagueSmith(1x2).png");
    public static Texture prupleTurt = new Texture("64 pix/PurpleTurtle(1x2).png");
    public static Texture realityReaver = new Texture("64 pix/RealityReaver(2x4).png");
    public static Texture rift = new Texture("64 pix/Rift(1x2).png");
    public static Texture rorc = new Texture("64 pix/Rorc(1x2).png");
    public static Texture sandScorp = new Texture("64 pix/SandScorpion(2x4).png");
    public static Texture seacrestSerp = new Texture("64 pix/SeacrestSerpent(2x4).png");
    public static Texture seaSerp = new Texture("64 pix/SeaSerpent(1x2).png");
    public static Texture serpSpawn = new Texture("64 pix/SerpentSpawn(1x2).png");
    public static Texture slime = new Texture("64 pix/Slime(1x3).png");
    public static Texture snakeGod = new Texture("64 pix/SnakeGodEssence(1x2).png");
    public static Texture spectre = new Texture("64 pix/Spectre(1x2).png");
    public static Texture sprit = new Texture("64 pix/SpiritofLight(2x3).png");
    public static Texture spit = new Texture("64 pix/Spitter(1x2).png");
    public static Texture stoneGolem = new Texture("64 pix/StoneGolem(1x2).png");
    public static Texture svenBarb = new Texture("64 pix/SvenBarb(1x2).png");
    public static Texture thornFire = new Texture("64 pix/Thornfire_Wolf(1x2).png");
    public static Texture undeadGlad = new Texture("64 pix/UndeadGladiator(1x2).png");
    public static Texture vexclaw = new Texture("64 pix/Vexclaw(2x4).png");
    public static Texture valCONG = new Texture("64 pix/Vulcanocong(1x2).png");
    public static Texture waspOID = new Texture("64 pix/Waspoid(1x2).png");
    public static Texture waterElemental = new Texture("64 pix/WaterElemental(1x2).png");
    public static Texture waterSerp = new Texture("64 pix/WaterSerpent(1x2).png");
    public static Texture weakDemon = new Texture("64 pix/Weak(1x2).png");
    public static Texture werewolf = new Texture("64 pix/Werewolf(1x2).png");
    public static Texture wyvern = new Texture("64 pix/Wyvern(1x2).png");
    public static Texture yalahar = new Texture("64 pix/YalaharPriest(1x2).png");
    public static Texture shiversleep = new Texture("64 pix/Shiversleep(1x2).png");
    public static Texture armadile = new Texture("64 pix/Armadile(1x2).png");
    public static Texture darakan = new Texture("64 pix/Darakan(1x2).png");
    public static Texture frostservant = new Texture("64 pix/FrostServant(1x2).png");
    public static Texture hearldofdoom = new Texture("64 pix/Herald_of_Doom(1x2).png");
    public static Texture lizardmage = new Texture("64 pix/Lizard_Mage(1x2).png");
    public static Texture menace = new Texture("64 pix/Menace(1x2).png");
    public static Texture tiger = new Texture("64 pix/Tiger(1x2).png");
    public static Texture witch = new Texture("64 pix/Witch(1x2).png");
    public static Texture ashmunrah = new Texture("64 pix/Ashmunrah(1x2).png");
    public static Texture askarakdemon = new Texture("64 pix/AskarakDemon(1x2).png");
    public static Texture askaraklord = new Texture("64 pix/AskarakLord(1x2).png");
    public static Texture askarakprince = new Texture("64 pix/AskarakPrince(1x2).png");
    public static Texture behemoth = new Texture("64 pix/Behemoth(1x2).png");
    public static Texture blightwalker = new Texture("64 pix/BlightWalker(1x2).png");
    public static Texture bluedjinn = new Texture("64 pix/BlueDjinn(1x2).png");
    public static Texture crystalspider = new Texture("64 pix/CrystalSpiders(1x2).png");
    public static Texture cursedhand = new Texture("64 pix/CursedHand(1x2).png");
    public static Texture cyclopssmith = new Texture("64 pix/CyclopsSmith(1x2).png");
    public static Texture damagedworkergolem = new Texture("64 pix/DamagedWorkerGolem(1x2).png");
    public static Texture diamondservant = new Texture("64 pix/DiamondServant(1x2).png");
    public static Texture goldenservant = new Texture("64 pix/GoldenServant(1x2).png");
    public static Texture dragonhatch = new Texture("64 pix/DragonHatch(1x2).png");
    public static Texture dragonlordhatch = new Texture("64 pix/DragonLordHatchling(1x2).png");
    public static Texture drakenabomination = new Texture("64 pix/DrakenAbomination(1x2).png");
    public static Texture drakenelite = new Texture("64 pix/DrakenElite(1x2).png");
    public static Texture drakenspellweaver = new Texture("64 pix/DrakenSpellweaver(1x2).png");
    public static Texture drakenwarmaster = new Texture("64 pix/DrakenWarmaster(1x2).png");
    public static Texture efreet = new Texture("64 pix/Efreet(1x2).png");
    public static Texture elderwyrm = new Texture("64 pix/ElderWyrm(1x2).png");
    public static Texture eyeobserver = new Texture("64 pix/EyeObserver(1x2).png");
    public static Texture frostdragon = new Texture("64 pix/FrostDragon(1x2).png");
    public static Texture frostgiant = new Texture("64 pix/FrostGiant(1x2).png");
    public static Texture frostgiantess = new Texture("64 pix/FrostGiantess(1x2).png");
    public static Texture ghastlydragon = new Texture("64 pix/GhastlyDragon(1x2).png");
    public static Texture gloothpoweredmino = new Texture("64 pix/GloothPoweredMino(1x2).png");
    public static Texture gnomeevil = new Texture("64 pix/GnomeEvil(1x2).png");
    public static Texture greendjinn = new Texture("64 pix/GreenDjinn(1x2).png");
    public static Texture lich = new Texture("64 pix/Lich(1x2).png");
    public static Texture minoamazon = new Texture("64 pix/MinoAmazon(1x2).png");
    public static Texture pharoh = new Texture("64 pix/Pharoh(1x2).png");
    public static Texture shaburaklord = new Texture("64 pix/ShaburakLord(1x2).png");
    public static Texture shaburakprince = new Texture("64 pix/ShaburakPrince(1x2).png");
    public static Texture silencer = new Texture("64 pix/Silencer(1x2).png");
    public static Texture spidriselite = new Texture("64 pix/SpidrisElite(1x2).png");
    public static Texture thepalecount = new Texture("64 pix/ThePaleCount(1x2).png");
    public static Texture tyrn = new Texture("64 pix/Tyrn(1x2).png");
    public static Texture undeaddragon = new Texture("64 pix/UndeadDragon(1x2).png");
    public static Texture vampirebride = new Texture("64 pix/VampireBride(1x2).png");
    public static Texture vampireoverlord = new Texture("64 pix/VampireOverlord(1x2).png");
    public static Texture vampireviscount = new Texture("64 pix/VampireViscount(1x2).png");
    public static Texture wargolem = new Texture("64 pix/WarGolem(1x2).png");
    public static Texture wormpriestess = new Texture("64 pix/WormPriestess(1x2).png");
    public static Texture wyrm = new Texture("64 pix/Wyrm(1x2).png");
    public static Texture yeti = new Texture("64 pix/Yeti(1x2).png");
    public static Texture marid = new Texture("64 pix/Marid(1x2).png");
    public static Texture metalgargoyle = new Texture("64 pix/MetalGargoyle(1x2).png");
    public static Texture moohtant = new Texture("64 pix/Moohtant(1x2).png");
    public static Texture beholder = new Texture("64 pix/Beholder(1x2).png");
    public static Texture elderbeholder = new Texture("64 pix/ElderBeholder(1x2).png");
    public static Texture evileye = new Texture("64 pix/TheEvilEye(1x2).png");

    public static Texture dryad = new Texture("64 pix/Dryad(2x4).png");
    public static Texture hypnotoad = new Texture("64 pix/Hypno_Toad(2x4).png");
    public static Texture ironblight = new Texture("64 pix/IronBlight(2x8).png");
    public static Texture lion = new Texture("64 pix/Lion(2x4).png");
    public static Texture cyclops = new Texture("64 pix/Cyclops(2x4).png");
    public static Texture dragongreen = new Texture("64 pix/DragonGreen(2x4).png");
    public static Texture dragonling = new Texture("64 pix/Dragonling(2x4).png");
    public static Texture dragonwarden = new Texture("64 pix/DragonWarden(2x4).png");
    public static Texture dragonzyrtarch = new Texture("64 pix/DragonZyrtarch(2x4).png");
    public static Texture draptor = new Texture("64 pix/Draptor(2x4).png");
    public static Texture giantspider = new Texture("64 pix/GiantSpider(2x4).png");
    public static Texture gravedigger = new Texture("64 pix/Gravedigger(2x4).png");
    public static Texture ogrebrute = new Texture("64 pix/OgreBrute(2x4).png");
    public static Texture ogreshaman = new Texture("64 pix/OgreShaman(2x4).png");
    public static Texture professormaxxen = new Texture("64 pix/ProfessorMaxxen(2x4).png");
    public static Texture tarantula = new Texture("64 pix/Tarantula(2x4).png");
    public static Texture tazhadur = new Texture("64 pix/Tazhadur(2x4).png");
    public static Texture thefirstdragon = new Texture("64 pix/TheFirstDragon(2x4).png");
    public static Texture wailingwidow = new Texture("64 pix/WailingWidow(2x4).png");
    public static Texture walker = new Texture("64 pix/Walker(2x4).png");
    public static Texture zorvorax = new Texture("64 pix/Zorvorax(2x4).png");

    // say 2x8 really 2x4
    public static Texture orcravanger = new Texture("64 pix/Orc_Ravanger(2x8).png");
    public static Texture orclops = new Texture("64 pix/Orclops_Doomhauler(2x8).png");
    public static Texture pitberserker = new Texture("64 pix/Pit_Berserker(2x8).png");
    public static Texture pitblacking = new Texture("64 pix/Pit_Blackling(2x8).png");




    /** Save Animations here */
    public static Animation<TextureRegion> ExplosionAnimation = Play.fourFrameAnimationCreator(spriteTextures.explosionSpritesTexture, 4,  4, .0001f);
    public static Animation<TextureRegion> LightningOrbAnimation = Play.fourFrameAnimationCreator(spriteTextures.LightningOrbSprites, 4,  4, .1f);

    public static Texture sheet(Play.MonsterType monster){
        switch(monster){
            case ASHMUNRAH:
                return ashmunrah;
            case CYCLOPS:
                return cyclops;
            case BEHOLDER:
                return beholder;
            case ELDERBEHOLDER:
                return elderbeholder;
            case EVILEYE:
                return evileye;
            case DRAGONGREEN:
                return dragongreen;
            case DRAGONLING:
                return dragonling;
            case DRAGONWARDEN:
                return dragonwarden;
            case DRAGONZYRTARCH:
                return dragonzyrtarch;
            case DRAPTOR:
                return draptor;
            case ASKARADEMON:
                return askarakdemon;
            case ASKARALORD:
                return askaraklord;
            case ASKARAPRINCE:
                return askarakprince;
            case BEHEMOTH:
                return behemoth;
            case BLIGHTWALKER:
                return blightwalker;
            case BLUEDJINN:
                return bluedjinn;
            case CRYSTALSPIDERS:
                return crystalspider;
            case CURSEDHAND:
                return cursedhand;
            case CYCLOPSSMITH:
                return cyclopssmith;
            case DAMAGEDWORKERGOLEM:
                return damagedworkergolem;
            case DIAMONDSERVANT:
                return diamondservant;
            case DRAGONHATCH:
                return dragonhatch;
            case DRAGONLORDHATCH:
                return dragonlordhatch;
            case DRAKENABOMINATION:
                return drakenabomination;
            case DRAKENELITE:
                return drakenelite;
            case DRAKENSPELLWEAVER:
                return drakenspellweaver;
            case DRAKENWARMASTER:
                return drakenwarmaster;
            case EFREET:
                return efreet;
            case ELDERWYRM:
                return elderwyrm;
            case MARID:
                return marid;
            case METALGARGOYLE:
                return metalgargoyle;
            case MOOHTANT:
                return moohtant;
            case GIANTSPIDER:
                return giantspider;
            case GRAVEDIGGER:
                return gravedigger;
            case OGREBRUTE:
                return ogrebrute;
            case OGRESHAMAN:
                return ogreshaman;
            case PROFESSORMAXXEN:
                return professormaxxen;
            case TARANTULA:
                return tarantula;
            case TAZHADUR:
                return tazhadur;
            case THEFIRSTDRAGON:
                return thefirstdragon;
            case WAILINGWIDOW:
                return wailingwidow;
            case WALKER:
                return walker;
            case ZORVORAX:
                return zorvorax;
            case EYEOBSERVER:
                return eyeobserver;
            case FROSTDRAGON:
                return frostdragon;
            case FROSTGIANT:
                return frostgiant;
            case FROSTGIANTESS:
                return frostgiantess;
            case GHASTLYDRAGON:
                return ghastlydragon;
            case GLOOTHMINO:
                return gloothpoweredmino;
            case GNOMEEVIL:
                return gnomeevil;
            case GOLDENSERVANT:
                return goldenservant;
            case GREENDJINN:
                return greendjinn;
            case LICH:
                return lich;
            case MINOAMAZON:
                return minoamazon;
            case PHAROH:
                return pharoh;
            case SHABURAKLORD:
                return shaburaklord;
            case SHABURAKPRINCE:
                return shaburakprince;
            case SILENCER:
                return silencer;
            case SPIDRISELITE:
                return spidriselite;
            case THEPALECOUNT:
                return thepalecount;
            case TYRN:
                return tyrn;
            case UNDEADDRAGON:
                return undeaddragon;
            case VAMPIREBRIDE:
                return vampirebride;
            case VAMPIREOVERLORD:
                return vampireoverlord;
            case VAMPIREVISCOUNT:
                return vampireviscount;
            case WARGOLEM:
                return wargolem;
            case WORMPRIESTESS:
                return wormpriestess;
            case WYRM:
                return wyrm;
            case YETI:
                return yeti;
            case DEMON:
                return demonTexture;
            case DRAGON:
                return dragonTexture ;
            case HYDRA:
                return hydraTexture;
            case BRITTLESKELETON:
                return brittleSkelly;
            case BUNNY:
                return bunny;
            case DEMONSKELETON:
                return demonSkelly;
            case ELITEORC:
                return eliteOrc;
            case GHOST:
                return ghost;
            case GOZZLER:
                return gozz;
            case ORCBASE:
                return orc;
            case ORCLEADER:
                return orcLeader;
            case PIRATEBUCANEER:
                return pirateBcaneer;
            case PIRATECUTTHROAT:
                return pirateCut;
            case PRIESTESS:
                return priestess;
            case RIFTFRAGMENT:
                return rithFrag;
            case WATERELEMENT:
                return puddle;
            case SQUIRREL:
                return squirrel;
            case VAMPIRE:
                return vamp;
            case WARLOCK:
                return warlock;
            case ZABWARLOCK:
                return zab;
            case AMBASSADOR:
                return ambassador;
            case ANCIENTSCARAB:
                return ancientScar;
            case BARBAXE :
                return barbAx;
            case BANSHEE:
                return banshee;
            case MUMMY:
                return mummy;
            case WRAITH:
                return betrayer;
            case BOGRAIDER:
                return bogRaider;
            case BONEBEAST:
                return boneBeast;
            case TOPHATNOBLE:
                return botHat;
            case CARNIPHILA:
                return carniphila;
            case UNDEADCAVEBEAR:
                return caveBear;
            case CLIFFSTRIDER:
                return cliffstrider;
            case CORLERIANBARB:
                return corlerianbarb;
            case CRAWLER:
                return crawler;
            case CRUSHEDWALKER:
                return crushedWalker;
            case CRYSTALWOLF:
                return crystalWolf;
            case DARKSTALKER:
                return darkstalker;
            case DEATHSTRIKE:
                return deathStrike;
            case DEEPTERROR:
                return deepTerror;
            case DESTROYER:
                return destroyer;
            case DEVORGA:
                return devorga;
            case DIABOLICIMP:
                return diabloicImp;
            case EARTHELMENTAL:
                return earthElemental;
            case GLOOTH:
                return empoweredGlooth;
            case ENERGYELEMENTAL:
                return energyEle1;
            case OLDENERGYELEMENTAL:
                return energyEle2;
            case ENERGYSCORPION:
                return energyScorp;
            case ENRAGEDCRYSTALGOLEM:
                return enragedCrystalGolem;
            case ENRAGEDGOLEM:
                return enragedGolem;
            case ETERNALGUARD:
                return eternalGuard;
            case FERMRUMBRAS:
                return ferm;
            case GHOSTFERMRUMBRAS:
                return ghostFerm;
            case FIREELEMENTAL:
                return fireElemental;
            case GARGOYLE:
                return gargoyle;
            case GLOOMBRINGER:
                return gloombringer;
            case GLOOTHMASHER:
                return gloothMasher;
            case GORGON:
                return gorgon;
            case GRIM:
                return grim;
            case GRIMLEECH:
                return grimLeech;
            case HELLFLAYER:
                return hellflayer;
            case HELLHOUND:
                return hellhound;
            case HIVEROVERSEER:
                return hiveOverseer;
            case INFECTEDWEEPER:
                return infectedWeeper;
            case INFERNALIST:
                return infernalist;
            case JACKOLANTERN:
                return jackolatern;
            case JUGGERNAUNT:
                return juggernaut;
            case LADYBUG:
                return ladybug;
            case LIZARDCHOSEN:
                return lizardChosen;
            case LIZARDHIGHGUARD:
                return lizardHighGuard;
            case LIZARDLEGIONAIRE:
                return lizardLegionaire;
            case LIZARDPRIEST:
                return lizardPriest;
            case LIZARDZAOGUN:
                return lizardZaogun;
            case LOSTSOUL:
                return lostSoul;
            case MADMAGE:
                return madMage;
            case MASSIVERFIREELEMENTAL:
                return massFireElemental;
            case MEDUSA:
                return medusa;
            case MIDNIGHTPANTHER:
                return midnightPanther;
            case NIGHTMAREGAZ:
                return nightmaregaz;
            case NOBELLION:
                return nobelLion;
            case ORCMAURADER:
                return orcMaurader;
            case ORCRIDER:
                return orcRider;
            case OREWALKER:
                return oreWalker;
            case PHRODOMO:
                return phodomo;
            case PINATADRAGON:
                return pinataDragon;
            case PIRATECORSAIR:
                return pirateCorsair;
            case PIRATEGHOST:
                return pirateGhost;
            case PIRATEMARAUDER:
                return pirateMarauder;
            case PITBATTLER1:
                return pitBat1;
            case PITBATTLER2:
                return pitBat2;
            case PLAGUESMITH:
                return plagueSmith;
            case PURPLETURTLE:
                return prupleTurt;
            case REALITYREAVER:
                return realityReaver;
            case RIFT:
                return rift;
            case RORC:
                return rorc;
            case SANDSCORPION:
                return sandScorp;
            case SEACRESTSERPENT:
                return seacrestSerp;
            case SEASERPENT:
                return seaSerp;
            case SERPENTSPAWN:
                return serpSpawn;
            case SLIME:
                return slime;
            case SNAKEGOD:
                return snakeGod;
            case SPECTRE:
                return spectre;
            case SPIRIT:
                return sprit;
            case SPITTER:
                return spit;
            case STONEGOLEM:
                return stoneGolem;
            case SVENBARB:
                return svenBarb;
            case THORNFIREWOLF:
                return thornFire;
            case UNDEADGLADIATOR:
                return undeadGlad;
            case VEXCLAW:
                return vexclaw;
            case VULCONGRA:
                return valCONG;
            case WASPOID:
                return waspOID;
            case WATERELEMENTAL:
                return waterElemental;
            case WATERSERPENT:
                return waterSerp;
            case WEAKDEMON:
                return  weakDemon;
            case WEREWOLF:
                return werewolf;
            case WYVERN:
                return wyvern;
            case YALAHAR:
                return yalahar;
            case ACOLYTE:
                return acolyte;
            case BLOODFIST:
                return bloodfist;
            case BLOODHAND:
                return bloodhand;
            case BLOOMOFDOOM:
                return bloomofdoom;
            case DARKAPPRENTICE:
                return darkapprentice;
            case DOCTOR:
                return doctor;
            case DOOMSDAY:
                return doomsday;
            case ENRAGEDGHOST:
                return enragedghost;
            case EYESERVANT:
                return eyeservant;
            case FIREDEVIL:
                return firedevit;
            case NECROMANCER:
                return necromancer;
            case ORCBERSERKER:
                return orcberserker;
            case ORCWARLORD:
                return orcwarlord;
            case ORCWARRIOR:
                return orcwarrior;
            case SHADOWPUPIL:
                return shadowpupil;
            case DAWNASURA:
                return dawnasura;
            case GOBLINBASIC:
                return goblinbasic;
            case MIDNIGHTASURA:
                return midnightasura;
            case ORCSHAMAN:
                return orcshaman;
            case ORCSPEARMAN:
                return orcspearman;
            case DEATHSLICER:
                return deathslicer;
            case ARMADILE:
                return armadile;
            case DARAKAN:
                return darakan;
            case FROSTSERVANT:
                return frostservant;
            case HEARLDOFDOOM:
                return hearldofdoom;
            case LIZARDMAGE:
                return lizardmage;
            case MENACE:
                return menace;
            case TIGER:
                return tiger;
            case WITCH:
                return witch;
            case SHIVERSLEEP:
                return shiversleep;
            case DRYAD:
                return dryad;
            case HYPNOTOAD:
                return hypnotoad;
            case IRONBLIGHT:
                return ironblight;
            case LION:
                return lion;
            case ORCRAVANGER:
                return orcravanger;
            case ORCLOPS:
                return orclops;
            case PITBERSERKER:
                return pitberserker;
            case PITBLACKLING:
                return pitblacking;
            case DONALDTRUMP:
                return DonaldTrump;
        }
        return basic32;
    }

    public static Texture stand(Play.MonsterType monster){

        switch(monster){

            case BRITTLESKELETON:
            case BUNNY:
            case DEMONSKELETON:
            case ELITEORC:
            case GHOST:
            case GOZZLER:
            case ORCBASE:
            case ORCLEADER:
            case PIRATEBUCANEER:
            case PIRATECUTTHROAT:
            case PRIESTESS:
            case RIFTFRAGMENT:
            case WATERELEMENT:
            case SQUIRREL:
            case VAMPIRE:
            case WARLOCK:
            case ZABWARLOCK:
            case ACOLYTE:
            case BLOODFIST:
            case BLOODHAND:
            case BLOOMOFDOOM:
            case DARKAPPRENTICE:
            case DOCTOR:
            case DOOMSDAY:
            case ENRAGEDGHOST:
            case EYESERVANT:
            case FIREDEVIL:
            case NECROMANCER:
            case ORCBERSERKER:
            case ORCWARLORD:
            case ORCWARRIOR:
            case SHADOWPUPIL:
            case DAWNASURA:
            case GOBLINBASIC:
            case MIDNIGHTASURA:
            case ORCSHAMAN:
            case ORCSPEARMAN:
            case DEATHSLICER:
            case MUMMY:
            case BANSHEE:
                return basic32;



        }
        return basic64;
    }

    public static int COL(Play.MonsterType monster){
        switch (monster){
            case DEMON:
            case DRAGON:
            case BRITTLESKELETON:
            case ORCBASE:
            case CARNIPHILA:
            case UNDEADCAVEBEAR:
            case CRYSTALWOLF:
            case DEEPTERROR:
            case GLOOTH:
            case ENERGYELEMENTAL:
            case OLDENERGYELEMENTAL:
            case ENERGYSCORPION:
            case GHOSTFERMRUMBRAS:
            case GRIMLEECH:
            case HELLFLAYER:
            case JACKOLANTERN:
            case LADYBUG:
            case MIDNIGHTPANTHER:
            case NOBELLION:
            case PINATADRAGON:
            case PITBATTLER1:
            case PITBATTLER2:
            case REALITYREAVER:
            case SANDSCORPION:
            case SEACRESTSERPENT:
            case VEXCLAW:
            case DAWNASURA:
            case GOBLINBASIC:
            case MIDNIGHTASURA:
            case ORCSHAMAN:
            case ORCSPEARMAN:
            case DRYAD:
            case HYPNOTOAD:
            case IRONBLIGHT:
            case LION:
            case ORCRAVANGER:
            case ORCLOPS:
            case PITBERSERKER:
            case PITBLACKLING:
            case DRAGONGREEN:
            case DRAGONLING:
            case DRAGONWARDEN:
            case DRAGONZYRTARCH:
            case CYCLOPS:
            case DRAPTOR:
            case ZORVORAX:
            case WALKER:
            case WAILINGWIDOW:
            case THEFIRSTDRAGON:
            case TAZHADUR:
            case TARANTULA:
            case PROFESSORMAXXEN:
            case OGREBRUTE:
            case OGRESHAMAN:
            case GRAVEDIGGER:
            case GIANTSPIDER:
                return 4;
            case RIFTFRAGMENT:
            case WATERELEMENT:
            case SPIRIT:
            case SLIME:
            case DEATHSLICER:
                return 3;
            case DONALDTRUMP:
                return 1;

        }

        return 2;

    }

    public static int ROW(Play.MonsterType monster){
        switch (monster){
            case DEMON:
            case DRAGON:
            case BRITTLESKELETON:
            case ORCBASE:
            case RIFTFRAGMENT:
            case CARNIPHILA:
            case UNDEADCAVEBEAR:
            case CRYSTALWOLF:
            case DEEPTERROR:
            case GLOOTH:
            case ENERGYSCORPION:
            case GHOSTFERMRUMBRAS:
            case GRIMLEECH:
            case HELLFLAYER:
            case LADYBUG:
            case MIDNIGHTPANTHER:
            case NOBELLION:
            case PINATADRAGON:
            case PITBATTLER1:
            case PITBATTLER2:
            case REALITYREAVER:
            case SANDSCORPION:
            case SEACRESTSERPENT:
            case SPIRIT:
            case VEXCLAW:
            case DAWNASURA:
            case GOBLINBASIC:
            case MIDNIGHTASURA:
            case ORCSHAMAN:
            case ORCSPEARMAN:
            case DRYAD:
            case HYPNOTOAD:
            case IRONBLIGHT:
            case LION:
            case ORCRAVANGER:
            case ORCLOPS:
            case PITBERSERKER:
            case PITBLACKLING:
            case DRAGONGREEN:
            case DRAGONLING:
            case DRAGONWARDEN:
            case DRAGONZYRTARCH:
            case CYCLOPS:
            case DRAPTOR:
            case ZORVORAX:
            case WALKER:
            case WAILINGWIDOW:
            case THEFIRSTDRAGON:
            case TAZHADUR:
            case TARANTULA:
            case PROFESSORMAXXEN:
            case OGREBRUTE:
            case OGRESHAMAN:
            case GRAVEDIGGER:
            case GIANTSPIDER:
            return 2;


        }

        return 1;
    }

    public static Item giveAWeapon(int level, String weapon){

        Random rand = new Random();
        int lvl;
        lvl = level;
        if(weapon.equals("random")) {
            int VAL;
            VAL = rand.nextInt(1000);

            if(VAL >= 0 && VAL <= 300) return new Bow(lvl);
            if(VAL >= 600 && VAL <= 799) return new LightningStaff(lvl);
            if(VAL >= 800 && VAL <= 929) return new Shuriken(lvl);
            if(VAL >= 301 && VAL <= 599) return new WizardStaff(lvl);
            if(VAL >= 999 && VAL <= 1000) return new LaserGun(lvl);
            if(VAL >= 930 && VAL <= 998) return new MiniGun(lvl);
            return new Bow(lvl);
        }
        else{
            switch (weapon){
                case "Bow":
                    return new Bow(lvl);
                case "AOE":
                    return new AOE(lvl);
                case "LaserGun":
                    return new LaserGun(lvl);
                case "LightningStaff":
                    return new LightningStaff(lvl);
                case "Magic":
                    return new Magic(lvl);
                case "Rune":
                    return new Rune(lvl);
                case "Shuriken":
                    return new Shuriken(lvl);
                case "Sword":
                    return new Sword(lvl);
                case "WizardStaff":
                    return new WizardStaff(lvl);
                case "MiniGun":
                    return new MiniGun(lvl);
            }
            return new Bow(lvl);
        }
    }

    public static Play.MonsterType makeAMonster(int spawnRange, int spawnStart){
        Random rand = new Random();
        int VAL;
        VAL = rand.nextInt(spawnRange) + spawnStart;

        switch(VAL) {
            case 0:
                return DEMON;
            case 1:
                return DRAGON;
            case 2 :
                return HYDRA;
            case 3:
                return BRITTLESKELETON;
            case 4:
                return BUNNY;
            case 5:
                return DEMONSKELETON;
            case 6:
                   return ELITEORC;
            case 7:
                return GHOST;
            case 8:
                   return GOZZLER;
            case 9:
                return VAMPIRE;
            case 10:
                return CLIFFSTRIDER;
            case 11:
                return PIRATEBUCANEER;
            case 12:
                return PIRATECUTTHROAT;
            case 13:
                return PRIESTESS;
            case 14:
                return RIFTFRAGMENT;
            case 15:
                return WATERELEMENT;
            case 16:
                return SQUIRREL;
            case 17:
                return ORCBASE;
            case 18:
                return WARLOCK;
            case 19:
                return ZABWARLOCK;
            case 20:
                return AMBASSADOR;
            case 21:
                return ANCIENTSCARAB;
            case 22:
                return BARBAXE;
            case 23:
                return WRAITH;
            case 24:
                return BOGRAIDER;
            case 25:
                return BONEBEAST;
            case 26:
                return TOPHATNOBLE;
            case 27:
                return CARNIPHILA;
            case 28:
                return UNDEADCAVEBEAR;
            case 29:
                return ORCLEADER;
            case 30:
                return CORLERIANBARB;
            case 31:
                return CRAWLER;
            case 32:
                return CRUSHEDWALKER;
            case 33:
                return CRYSTALWOLF;
            case 34:
                return     DARKSTALKER;
            case 35:
                return     DEATHSTRIKE;
            case 36:
                return     DEEPTERROR;
            case 37:
                return     DESTROYER;
            case 38:
                return     DEVORGA;
            case 39:
                return     DIABOLICIMP;
            case 40:
                return     EARTHELMENTAL;
            case 41:
                return     GLOOTH;
            case 42:
                return     ENERGYELEMENTAL;
            case 43:
                return     OLDENERGYELEMENTAL;
            case 44:
                return     ENERGYSCORPION;
            case 45:
                return     ENRAGEDCRYSTALGOLEM;
            case 46:
                return     ENRAGEDGOLEM;
            case 47:
                return ETERNALGUARD;
            case 48:
                return     FERMRUMBRAS;
            case 49:
                return GHOSTFERMRUMBRAS;
            case 50:
                return     FIREELEMENTAL;
            case 51:
                return     GARGOYLE;
            case 52:
                return     GLOOMBRINGER;
            case 53:
                return     GLOOTHMASHER;
            case 54:
                return     GORGON;
            case 55:
                return     GRIM;
            case 56:
                return     GRIMLEECH;
            case 57:
                return     HELLFLAYER;
            case 58:
                return     HELLHOUND;
            case 59:
                return     HIVEROVERSEER;
            case 60:
                return INFECTEDWEEPER;
            case 61:
                    return     INFERNALIST;
            case 62:
                return     JACKOLANTERN;
            case 63:
                return     JUGGERNAUNT;
            case 64:
                return     LADYBUG;
            case 65:
                return     LIZARDCHOSEN;
            case 66:
                return LIZARDHIGHGUARD;
            case 67:
                return LIZARDLEGIONAIRE;
            case 68:
                return     LIZARDPRIEST;
            case 69:
                return     LIZARDZAOGUN;
            case 70:
                return     LOSTSOUL;
            case 71:
                return     MADMAGE;
            case 72:
                return     MASSIVERFIREELEMENTAL;
            case 73:
                return     MEDUSA;
            case 74:
                return     MIDNIGHTPANTHER;
            case 75:
                return     NIGHTMAREGAZ;
            case 76:
                return     NOBELLION;
            case 77:
                return     ORCMAURADER;
            case 78:
                return     ORCRIDER;
            case 79:
                return     OREWALKER;
            case 80:
                return     PHRODOMO;
            case 81:
                return     PINATADRAGON;
            case 82:
                return     PIRATECORSAIR;
            case 83:
                return     PIRATEGHOST;
            case 84:
                return   PIRATEMARAUDER;
            case 85:
                return     PITBATTLER1;
            case 86:
                return     PITBATTLER2;
            case 87:
                return     PLAGUESMITH;
            case 88:
                return     PURPLETURTLE;
            case 89:
                return     REALITYREAVER;
            case 90:
                return     RIFT;
            case 91:
                return     RORC;
            case 92:
                return     SANDSCORPION;
            case 93:
                return     SEACRESTSERPENT;
            case 94:
                return     SEASERPENT;
            case 95:
                return     SERPENTSPAWN;
            case 96:
                return     SLIME;
            case 97:
                return     SNAKEGOD;
            case 98:
                return     SPECTRE;
            case 99:
                return     SPIRIT;
            case 100:
                return     SPITTER;
            case 101:
                return       STONEGOLEM;
            case 102:
                return       SVENBARB;
            case 103:
                return       THORNFIREWOLF;
            case 104:
                return       UNDEADGLADIATOR;
            case 105:
                return      VEXCLAW;
            case 106:
                return       VULCONGRA;
            case 107:
                return       WASPOID;
            case 108:
                return       WATERELEMENTAL;
            case 109:
                return       WATERSERPENT;
            case 110:
                return       WEAKDEMON;
            case 111:
                return       WEREWOLF;
            case 112:
                return       WYVERN;
            case 113:
                return       YALAHAR;
            case 114:
                return ACOLYTE;
            case 115:
                return BLOODFIST;
            case 116:
                return BLOODHAND;
            case 117:
                return BLOOMOFDOOM;
            case 118:
                return DARKAPPRENTICE;
            case 119:
                return DOCTOR;
            case 120:
                return DOOMSDAY;
            case 121:
                return ENRAGEDGHOST;
            case 122:
                return EYESERVANT;
            case 123:
                return FIREDEVIL;
            case 124:
                return NECROMANCER;
            case 125:
                return ORCBERSERKER;
            case 126:
                return ORCWARLORD;
            case 127:
                return ORCWARRIOR;
            case 128:
                return SHADOWPUPIL;
            case 129:
                return DAWNASURA;
            case 130:
                return GOBLINBASIC;
            case 131:
                return MIDNIGHTASURA;
            case 132:
                return ORCSHAMAN;
            case 133:
                return ORCSPEARMAN;
            case 134:
                return DEATHSLICER;
            case 135:
                return ARMADILE;
            case 136:
                return DARAKAN;
            case 137:
                return FROSTSERVANT;
            case 138:
                return HEARLDOFDOOM;
            case 139:
                return LIZARDMAGE;
            case 140:
                return MENACE;
            case 141:
                return TIGER;
            case 142:
                return WITCH;
            case 143:
                return SHIVERSLEEP;
            case 144:
                return DRYAD;
            case 145:
                return HYPNOTOAD;
            case 146:
                return IRONBLIGHT;
            case 147:
                return LION;
            case 148:
                return ORCRAVANGER;
            case 149:
                return ORCLOPS;
            case 150:
                return PITBERSERKER;
            case 151:
                return PITBLACKLING;
            case 152:
                return MUMMY;
            case 153:
                return BANSHEE;
            case 154:
                return CYCLOPS;
            case 155:
                return DRAGONGREEN;
            case 156:
                return  DRAGONLING;
            case 157:
                return  DRAGONWARDEN;
            case 158:
                return  DRAGONZYRTARCH;
            case 159:
                return  DRAPTOR;
            case 160:
                return ASHMUNRAH;
            case 161:
                return ASKARADEMON;
            case 162:
                return ASKARALORD;
            case 163:
                return ASKARAPRINCE;
            case 164:
                return  BEHEMOTH;
            case 165:
                return BLIGHTWALKER;
            case 166:
                return BLUEDJINN;
            case 167:
                return CRYSTALSPIDERS;
            case 168:
                return CURSEDHAND;
            case 169:
                return CYCLOPSSMITH;
            case 170:
                return DAMAGEDWORKERGOLEM;
            case 171:
                return DIAMONDSERVANT;
            case 172:
                return DRAGONHATCH;
            case 173:
                return DRAGONLORDHATCH;
            case 174:
                return DRAKENABOMINATION;
            case 175:
                return DRAKENELITE;
            case 176:
                return DRAKENSPELLWEAVER;
            case 177:
                return DRAKENWARMASTER;
            case 178:
                return EFREET;
            case 179:
                return ELDERWYRM;
            case 180:
                return EYEOBSERVER;
            case 181:
                return FROSTDRAGON;
            case 182:
                return FROSTGIANT;
            case 183:
                return FROSTGIANTESS;
            case 184:
                return GHASTLYDRAGON;
            case 185:
                return GLOOTHMINO;
            case 186:
                return GNOMEEVIL;
            case 187:
                return GOLDENSERVANT;
            case 188:
                return GREENDJINN;
            case 189:
                return LICH;
            case 190:
                return MINOAMAZON;
            case 191:
                return PHAROH;
            case 192:
                return SHABURAKLORD;
            case 193:
                return SHABURAKPRINCE;
            case 194:
                return SILENCER;
            case 195:
                return SPIDRISELITE;
            case 196:
                return THEPALECOUNT;
            case 197:
                return TYRN;
            case 198:
                return UNDEADDRAGON;
            case 199:
                return VAMPIREBRIDE;
            case 200:
                return VAMPIREOVERLORD;
            case 201:
                return VAMPIREVISCOUNT;
            case 202:
                return WARGOLEM;
            case 203:
                return WORMPRIESTESS;
            case 204:
                return WYRM;
            case 205:
                return YETI;
            case 206:
                return GIANTSPIDER;
            case 207:
                return  GRAVEDIGGER;
            case 208:
                return  OGREBRUTE;
            case 209:
                return  OGRESHAMAN;
            case 210:
                return  PROFESSORMAXXEN;
            case 211:
                return  TARANTULA;
            case 212:
                return  TAZHADUR;
            case 213:
                return  THEFIRSTDRAGON;
            case 214:
                return WAILINGWIDOW;
            case 215:
                return  WALKER;
            case 216:
                return  ZORVORAX;
            case 217:
                return MARID;
            case 218:
                return  METALGARGOYLE;
            case 219:
                return  MOOHTANT;
            case 220:
                return ELDERBEHOLDER;
            case 221:
                return BEHOLDER;
            case 222:
                return EVILEYE;
            case 223:
                return DONALDTRUMP;
        }
        return WEREWOLF;
    }

}
