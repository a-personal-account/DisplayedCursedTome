package displayedCursedTome.patches;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.GenericEventDialog;
import com.megacrit.cardcrawl.events.city.CursedTome;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.relics.*;
import javassist.CtBehavior;

import java.util.ArrayList;

@SpirePatch(clz = CursedTome.class, method = "buttonEffect")
public class DisplayBookPatch {
    @SpireInsertPatch(
            locator = Locator.class
    )
    public static void Insert(CursedTome __instance) {
        __instance.imageEventText.clearAllDialogs();
        __instance.imageEventText.setDialogOption(CursedTome.OPTIONS[5] + ReflectionHacks.getPrivate(__instance, CursedTome.class, "finalDmg") + CursedTome.OPTIONS[6], randomBook());
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(GenericEventDialog.class, "setDialogOption");
            return new int[]{ LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher)[5] };
        }
    }

    public static AbstractRelic randomBook() {
        ArrayList<AbstractRelic> possibleBooks = new ArrayList();// 152
        if (!AbstractDungeon.player.hasRelic(Necronomicon.ID)) {// 154
            possibleBooks.add(RelicLibrary.getRelic(Necronomicon.ID).makeCopy());// 155
        }

        if (!AbstractDungeon.player.hasRelic(Enchiridion.ID)) {// 158
            possibleBooks.add(RelicLibrary.getRelic(Enchiridion.ID).makeCopy());// 159
        }

        if (!AbstractDungeon.player.hasRelic(NilrysCodex.ID)) {// 162
            possibleBooks.add(RelicLibrary.getRelic(NilrysCodex.ID).makeCopy());// 163
        }

        if (possibleBooks.size() == 0) {// 166
            possibleBooks.add(RelicLibrary.getRelic(Circlet.ID).makeCopy());// 167
        }

        return possibleBooks.get(AbstractDungeon.miscRng.copy().random(possibleBooks.size() - 1));
    }
}
