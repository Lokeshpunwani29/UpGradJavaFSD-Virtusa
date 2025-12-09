package com.java.innerclient;


import com.java.innerclient.model.Agent;
import com.java.innerclient.model.Complaint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/")
    @ResponseBody
    public String login() {
        return """
        <!DOCTYPE html>
        <html>
        <head>
            <title>Login</title>
            <style>
                body {
                    margin: 0;
                    padding: 0;
                    height: 100vh;
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    background: linear-gradient(135deg, #667eea, #764ba2);
                    font-family: Arial, sans-serif;
                }

                .login-box {
                    background: white;
                    padding: 40px 50px;
                    border-radius: 12px;
                    text-align: center;
                    box-shadow: 0 10px 25px rgba(0,0,0,0.3);
                    width: 360px;
                }

                h2 {
                    margin-bottom: 10px;
                    color: #333;
                }

                p {
                    color: #666;
                    margin-bottom: 30px;
                }

                .github-btn {
                    text-decoration: none;
                    background: #24292e;
                    color: white;
                    padding: 12px 24px;
                    border-radius: 6px;
                    display: inline-block;
                    font-size: 16px;
                    transition: 0.3s;
                }

                .github-btn:hover {
                    background: #000;
                    transform: scale(1.05);
                }
            </style>
        </head>
        <body>

            <div class="login-box">
                <h2>Agent Complaint System</h2>
                <p>Welcome to Agent Register Complaint Service</p>

                <a class="github-btn" href="/oauth2/authorization/github">
                    Login with GitHub
                </a>
            </div>

        </body>
        </html>
        """;
    }

//    @GetMapping("/showAgent")
//    public List<Agent> AgentShow(@AuthenticationPrincipal OAuth2User user) {
//        return restTemplate.getForObject("http://AGENTSERVICE/agent/showAgent", List.class);
//    }

    @GetMapping("/showAgent")
    @ResponseBody
    public String showAgent(@AuthenticationPrincipal OAuth2User user) {

        List agents = restTemplate.getForObject(
                "http://AGENTSERVICE/agent/showAgent",
                List.class
        );

        StringBuilder rows = new StringBuilder();

        if (agents != null) {
            for (Object obj : agents) {
                Map agent = (Map) obj;

                rows.append("<tr>")
                        .append("<td>").append(agent.get("agentId")).append("</td>")
                        .append("<td>").append(agent.get("firstName")).append("</td>")
                        .append("<td>").append(agent.get("lastName")).append("</td>")
                        .append("<td>").append(agent.get("city")).append("</td>")
                        .append("<td>").append(agent.get("state")).append("</td>")
                        .append("<td>").append(agent.get("premiumPaid")).append("</td>")
                        .append("</tr>");
            }
        }

        // Detect provider: Google usually has "picture", GitHub has "avatar_url"
        boolean isGitHub = user.getAttribute("avatar_url") != null;

        String picture = isGitHub
                ? (String) user.getAttribute("avatar_url")
                : (String) user.getAttribute("picture");

        // THEME variables
        String bodyBg       = isGitHub ? "linear-gradient(135deg,#020617,#0f172a)" : "linear-gradient(135deg,#667eea,#764ba2)";
        String cardBg       = isGitHub ? "#111827" : "#ffffff";
        String cardShadow   = isGitHub ? "0 10px 25px rgba(0,0,0,0.6)" : "0 10px 25px rgba(0,0,0,0.3)";
        String textColor    = isGitHub ? "#e5e7eb" : "#333333";
        String subTextColor = isGitHub ? "#9ca3af" : "#555555";
        String thBg         = isGitHub ? "#24292e" : "#667eea";
        String thColor      = "#ffffff";
        String rowHoverBg   = isGitHub ? "#1f2933" : "#f2f2f2";
        String logoutBg     = isGitHub ? "#dc2626" : "#e74c3c";

        String providerLabel = isGitHub ? "GitHub" : "Google";

        StringBuilder html = new StringBuilder();

        html.append("<!DOCTYPE html>")
                .append("<html>")
                .append("<head>")
                .append("<title>Agent Dashboard</title>")
                .append("<style>")
                .append("body{margin:0;padding:20px;min-height:100vh;background:")
                .append(bodyBg)
                .append(";font-family:Arial,sans-serif;}")

                .append(".container{background:")
                .append(cardBg)
                .append(";padding:30px;border-radius:12px;max-width:900px;margin:auto;box-shadow:")
                .append(cardShadow)
                .append(";color:")
                .append(textColor)
                .append(";}")

                .append(".header{text-align:center;margin-bottom:25px;}")

                .append(".header img{width:90px;height:90px;border-radius:50%;margin-bottom:10px;border:3px solid ")
                .append(isGitHub ? "#2ea44f" : "#667eea")
                .append(";}")

                .append("h2{margin-bottom:5px;color:")
                .append(textColor)
                .append(";}")

                .append("p{margin:4px 0;color:")
                .append(subTextColor)
                .append(";}")

                .append("table{width:100%;border-collapse:collapse;margin-top:25px;}")
                .append("th,td{padding:12px;text-align:center;border-bottom:1px solid #374151;}")
                .append("th{background:")
                .append(thBg)
                .append(";color:")
                .append(thColor)
                .append(";}")
                .append("tr:hover{background:")
                .append(rowHoverBg)
                .append(";}")

                .append(".top-bar{display:flex;justify-content:space-between;align-items:center;margin-bottom:10px;}")
                .append(".provider-pill{font-size:13px;padding:4px 10px;border-radius:999px;border:1px solid ")
                .append(isGitHub ? "#2ea44f" : "#667eea")
                .append(";color:")
                .append(isGitHub ? "#bbf7d0" : "#333333")
                .append(";background:")
                .append(isGitHub ? "rgba(34,197,94,0.15)" : "rgba(102,126,234,0.15)")
                .append(";}")
                .append(".logout-btn a{text-decoration:none;background:")
                .append(logoutBg)
                .append(";color:white;padding:10px 22px;border-radius:6px;font-size:14px;}")
                .append(".logout-btn a:hover{filter:brightness(1.1);}")
                .append("</style>")
                .append("</head>")
                .append("<body>");

        html.append("<div class='container'>");

        html.append("<div class='top-bar'>")
                .append("<div class='provider-pill'>Logged in with ")
                .append(providerLabel)
                .append("</div>")
                .append("</div>");

        html.append("<div class='header'>")
                .append("<img src='").append(picture).append("' alt='profile'/>")
                .append("<h2>Welcome, ").append((String) user.getAttribute("name")).append("</h2>")
                .append("</div>");

        html.append("<h3 style='text-align:center;margin-top:10px;margin-bottom:10px;'>Agent List</h3>");

        html.append("<table>")
                .append("<tr>")
                .append("<th>Agent ID</th>")
                .append("<th>First Name</th>")
                .append("<th>Last Name</th>")
                .append("<th>City</th>")
                .append("<th>State</th>")
                .append("<th>Premium Paid</th>")
                .append("</tr>")
                .append(rows)
                .append("</table>");

        html.append("</div>");
        html.append("</body></html>");

        return html.toString();
    }


    @GetMapping("searchAgent/{AgentId}")
    public Agent searchAgent(@PathVariable int AgentId) {
        return restTemplate.getForObject("http://AGENTSERVICE/agent/searchAgent/"+AgentId, Agent.class);
    }

    @GetMapping("/showComplaint")
    public List<Agent> complaintShow() {
        return restTemplate.getForObject("http://REGISTERSERVICE/complaint/showComplaint", List.class);
    }

    @GetMapping("searchComplaint/{complaintId}")
    public Complaint searchComplaint(@PathVariable int complaintId) {
        return restTemplate.getForObject("http://REGISTERSERVICE/complaint/searchComplaint/"+complaintId, Complaint.class);
    }

}
