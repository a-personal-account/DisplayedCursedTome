package displayedCursedTome;

import basemod.BaseMod;
import basemod.ModPanel;
import basemod.interfaces.PostInitializeSubscriber;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

@SpireInitializer
public class BetterbookMod implements
        PostInitializeSubscriber {
    public static final Logger logger = LogManager.getLogger(BetterbookMod.class.getName());
    private static String modID;

    private static final String MODNAME = "Displayed Cursed Tome";
    private static final String AUTHOR = "raz";
    private static final String DESCRIPTION = "It now displays which book you're gonna get in the necronomicon event before spending the 10 extra life on the \"pick up\" option.";

    public static ArrayList<AbstractCard> inkedCardsList = new ArrayList<>();

    // =============== INPUT TEXTURE LOCATION =================
    public static final String BADGE_IMAGE = "betterbookResources/images/Badge.png";


    // =============== SUBSCRIBE, CREATE THE COLOR_GRAY, INITIALIZE =================

    public BetterbookMod() {
        BaseMod.subscribe(this);
    }

    @SuppressWarnings("unused")
    public static void initialize() {
        BetterbookMod defaultmod = new BetterbookMod();
    }


    @Override
    public void receivePostInitialize() {
        logger.info("Loading badge image and mod options");

        // Load the Mod Badge
        Texture badgeTexture = new Texture(BADGE_IMAGE);

        // Create the Mod Menu
        ModPanel settingsPanel = new ModPanel();

        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);
    }
}
