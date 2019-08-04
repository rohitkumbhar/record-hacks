package net.kumbhar.dx;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;


@SupportedAnnotationTypes({"net.kumbhar.dx.Hack", "net.kumbhar.dx.Hacks"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class HackChecker extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {


        final Set<? extends Element> singular = roundEnvironment.getElementsAnnotatedWith(Hack.class);
        for (Element element : singular) {
            final Hack hack = element.getAnnotation(Hack.class);
            checkHack(hack, element);
        }

        final Set<? extends Element> plural = roundEnvironment.getElementsAnnotatedWith(Hacks.class);
        for (Element element : plural) {
            final Hacks hacks = element.getAnnotation(Hacks.class);
            for (Hack hack : hacks.value()) {
                checkHack(hack, element);
            }
        }


        return true;
    }

    private void checkHack(Hack hack, Element element) {

        final String description = hack.description();
        final String trackedBy = hack.trackedBy();
        final String fixBeforeDate = hack.fixBeforeDate();
        final boolean expired = expired(fixBeforeDate, element);
        final Diagnostic.Kind kind = expired ? Diagnostic.Kind.ERROR : Diagnostic.Kind.MANDATORY_WARNING;

        final StringBuilder messageBuilder = new StringBuilder();
        if (expired) {
            messageBuilder.append("Expired Hack: ");
        } else {
            messageBuilder.append("Hack: ");
        }
        messageBuilder.append(description);

        if (!trackedBy.trim().equals("")) {
            messageBuilder.append(" Tracked by: ").append(trackedBy);
        }

        processingEnv.getMessager().printMessage(kind, messageBuilder.toString(), element);
    }

    private boolean expired(String fixBeforeDate, Element element) {

        if (fixBeforeDate == null || fixBeforeDate.trim().equals("")) {
            return false;
        }

        try {
            final Date expiryDate = new SimpleDateFormat("yyyy-MM-dd").parse(fixBeforeDate);
            return expiryDate.before(new Date());
        } catch (ParseException e) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
                    "Unable to parse date " + fixBeforeDate + ". Expected format is yyyy-MM-dd",
                    element);
        }

        return false;
    }

}
