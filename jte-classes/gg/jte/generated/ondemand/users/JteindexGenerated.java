package gg.jte.generated.ondemand.users;
import org.example.hexlet.NamedRoutes;
import org.example.hexlet.dto.users.UsersPage;
public final class JteindexGenerated {
	public static final String JTE_NAME = "users/index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,2,2,13,13,13,15,15,16,16,17,17,17,17,17,17,17,17,17,17,17,17,18,18,19,19,22,22,22,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UsersPage page) {
		jteOutput.writeContent("\n<!doctype html>\n<html lang=\"en\">\n<head>\n    <meta charset=\"utf-8\" />\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\n    <title>Hexlet Javalin Example</title>\n</head>\n<body>\n    <p>\n        ");
		if (page.getUsers().isEmpty()) {
			jteOutput.writeContent("\n            <p>Users not found</p>\n        ");
		} else {
			jteOutput.writeContent("\n            ");
			for (var user : page.getUsers()) {
				jteOutput.writeContent("\n                <a");
				var __jte_html_attribute_0 = NamedRoutes.userPath(user.getId());
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
					jteOutput.writeContent(" href=\"");
					jteOutput.setContext("a", "href");
					jteOutput.writeUserContent(__jte_html_attribute_0);
					jteOutput.setContext("a", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(">");
				jteOutput.setContext("a", null);
				jteOutput.writeUserContent(user.getName());
				jteOutput.writeContent("</a>\n            ");
			}
			jteOutput.writeContent("\n        ");
		}
		jteOutput.writeContent("\n    </p>\n</body>\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UsersPage page = (UsersPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
