package gg.jte.generated.ondemand.users;
import org.example.hexlet.NamedRoutes;
import org.example.hexlet.dto.users.BuildUserPage;
public final class JtebuildGenerated {
	public static final String JTE_NAME = "users/build.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,2,2,12,12,12,14,14,15,15,16,16,16,17,17,18,18,20,20,22,22,22,22,22,22,22,22,22,26,26,26,26,26,26,26,26,26,32,32,32,32,32,32,32,32,32,50,50,50,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, BuildUserPage page) {
		jteOutput.writeContent("\n<!doctype html>\n<html lang=\"en\">\n<head>\n    <meta charset=\"utf-8\" />\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\n    <title>Hexlet Javalin Example</title>\n</head>\n<body>\n");
		if (page.getErrors() != null) {
			jteOutput.writeContent("\n    <ul>\n        ");
			for (var validator : page.getErrors().values()) {
				jteOutput.writeContent("\n            ");
				for (var error : validator) {
					jteOutput.writeContent("\n                <li>");
					jteOutput.setContext("li", null);
					jteOutput.writeUserContent(error.getMessage());
					jteOutput.writeContent("</li>\n            ");
				}
				jteOutput.writeContent("\n        ");
			}
			jteOutput.writeContent("\n    </ul>\n");
		}
		jteOutput.writeContent("\n\n<form");
		var __jte_html_attribute_0 = NamedRoutes.usersPath();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
			jteOutput.writeContent(" action=\"");
			jteOutput.setContext("form", "action");
			jteOutput.writeUserContent(__jte_html_attribute_0);
			jteOutput.setContext("form", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(" method=\"post\">\n    <div>\n        <label>\n            Имя\n            <input type=\"text\" name=\"name\"");
		var __jte_html_attribute_1 = page.getName();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_1)) {
			jteOutput.writeContent(" value=\"");
			jteOutput.setContext("input", "value");
			jteOutput.writeUserContent(__jte_html_attribute_1);
			jteOutput.setContext("input", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(" />\n        </label>\n    </div>\n    <div>\n        <label>\n            Email\n            <input type=\"email\" required name=\"email\"");
		var __jte_html_attribute_2 = page.getEmail();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_2)) {
			jteOutput.writeContent(" value=\"");
			jteOutput.setContext("input", "value");
			jteOutput.writeUserContent(__jte_html_attribute_2);
			jteOutput.setContext("input", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(" />\n        </label>\n    </div>\n    <div>\n        <label>\n            Пароль\n            <input type=\"password\" required name=\"password\" />\n        </label>\n    </div>\n    <div>\n        <label>\n            Подтверждение пароля\n            <input type=\"password\" required name=\"passwordConfirmation\" />\n        </label>\n    </div>\n    <input type=\"submit\" value=\"Зарегистрировать\" />\n</form>\n</body>\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		BuildUserPage page = (BuildUserPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
