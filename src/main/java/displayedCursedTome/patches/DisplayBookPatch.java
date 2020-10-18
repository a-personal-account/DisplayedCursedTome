package displayedCursedTome.patches;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.events.GenericEventDialog;
import com.megacrit.cardcrawl.events.city.CursedTome;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import javassist.CtBehavior;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@SpirePatch(clz = CursedTome.class, method = "buttonEffect")
public class DisplayBookPatch {
    public static AbstractRelic book;

    @SpireInsertPatch(
            locator = Locator.class
    )
    public static void Insert(CursedTome __instance) {
        book = null;
        try {
            Method m = CursedTome.class.getDeclaredMethod("randomBook");
            m.setAccessible(true);
            m.invoke(__instance);
        } catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) { ex.printStackTrace(); }
        if(book != null) {
            __instance.imageEventText.clearAllDialogs();
            __instance.imageEventText.setDialogOption(CursedTome.OPTIONS[5] + ReflectionHacks.getPrivate(__instance, CursedTome.class, "finalDmg") + CursedTome.OPTIONS[6], book);
        }
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(GenericEventDialog.class, "setDialogOption");
            return new int[]{ LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher)[5] };
        }
    }
}
