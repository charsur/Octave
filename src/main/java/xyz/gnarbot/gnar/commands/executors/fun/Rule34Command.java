package xyz.gnarbot.gnar.commands.executors.fun;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import xyz.gnarbot.gnar.commands.handlers.Command;
import xyz.gnarbot.gnar.commands.handlers.CommandExecutor;
import xyz.gnarbot.gnar.utils.Note;

import java.util.List;
import java.util.Random;

@Command(aliases = {"rule", "rule34"},
        usage = "[query]",
        description = "Pulls a random rule 34 article from your keywords",
        showInHelp = false)
public class Rule34Command extends CommandExecutor {
    @Override
    public void execute(Note note, List<String> args) {
        if (note.getAuthor().hasRole("Fucking Teemo")) {
            String tag = "";
            try {
                for (String s : args) {
                    if (s.equals("_rule")) continue;
                    if (tag.equals("")) {
                        tag += ("&tags=" + s);
                    } else {
                        tag += ("+" + s);
                    }
                }
            } catch (Exception ignore) {
            }

            try {

                String xml = "http://rule34.xxx/index.php?page=dapi&s=post&q=index" + tag;

                Document document = Jsoup.connect(xml).parser(Parser.xmlParser()).get();

                Elements posts = document.getElementsByTag("post");

                int rand = new Random().nextInt(posts.size());

                Element target = posts.get(rand);

                String url;

                Attributes att = target.attributes();

                Attribute att2 = att.asList().get(2);

                url = att2.getValue();

                note.replyRaw("http:" + url);
            } catch (Exception e) {
                note.error("Please refer to rule 35.");
            }
        } else {
            note.error("Sorry, you must have the role `Fucking Teemo` to use this command!");
        }

    }
}