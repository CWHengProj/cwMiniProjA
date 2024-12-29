package com.cwheng.playOTG.miniProj.Service;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Service;

@Service
public class MarkdownConverter {
    //uses the commonmark dependency to convert RFM to HTML, reduce formatting inconsistencies
    private final Parser parser;
    private final HtmlRenderer renderer;

    public MarkdownConverter() {
        this.parser = Parser.builder().build();
        this.renderer = HtmlRenderer.builder().build();
        //lacking html sanitizer to avoid XSS attacks , do it if have time  
    }
    public String convertMarkdownToHtml(String markdown) {
        if (markdown == null || markdown.isEmpty()) {
            return "";
        }
        return renderer.render(parser.parse(markdown));
    }
}
