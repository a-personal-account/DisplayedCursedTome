package displayedCursedTome.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.city.CursedTome;
import com.megacrit.cardcrawl.rooms.EventRoom;
import com.megacrit.cardcrawl.screens.CombatRewardScreen;

@SpirePatch(clz = CombatRewardScreen.class, method = "open", paramtypez = {})
public class PreventRewardScreenOpeningPatch {
    @SpirePrefixPatch
    public static SpireReturn<Void> Prefix(CombatRewardScreen __instance) {
        if(AbstractDungeon.getCurrRoom() instanceof EventRoom && ((EventRoom) AbstractDungeon.getCurrRoom()).event instanceof CursedTome) {
            return SpireReturn.Return(null);
        }
        return SpireReturn.Continue();
    }
}
