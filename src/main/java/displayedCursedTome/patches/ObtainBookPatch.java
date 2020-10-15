package displayedCursedTome.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.city.CursedTome;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import javassist.CtBehavior;

@SpirePatch(clz = CursedTome.class, method = "randomBook")
public class ObtainBookPatch {
    @SpireInsertPatch(
            locator = Locator.class,
            localvars={"r"}
    )
    public static void Insert(CursedTome __instance, AbstractRelic r) {
        AbstractDungeon.combatRewardScreen.clear();
        AbstractDungeon.getCurrRoom().rewards.clear();
        r.instantObtain();
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractDungeon.class, "combatRewardScreen");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}
