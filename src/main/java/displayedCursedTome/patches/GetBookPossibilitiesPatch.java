package displayedCursedTome.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.city.CursedTome;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import javassist.CtBehavior;

import java.util.ArrayList;

@SpirePatch(clz = CursedTome.class, method = "randomBook")
public class GetBookPossibilitiesPatch {
    @SpireInsertPatch(
            locator = Locator.class,
            localvars={"possibleBooks"}
    )
    public static SpireReturn<Void> Insert(CursedTome __instance, ArrayList<AbstractRelic> possibleBooks) {
        if(DisplayBookPatch.book == null) {
            DisplayBookPatch.book = possibleBooks.get(AbstractDungeon.miscRng.copy().random(possibleBooks.size() - 1));
            return SpireReturn.Return(null);
        }
        return SpireReturn.Continue();
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractDungeon.class, "miscRng");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}
