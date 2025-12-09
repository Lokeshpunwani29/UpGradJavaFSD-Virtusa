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
    public String loginWithGoogle() {
        return """
        <!DOCTYPE html>
        <html>
        <head>
            <title>Google Login</title>
            <style>
                body {
                    margin: 0;
                    padding: 0;
                    height: 100vh;
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    background: linear-gradient(135deg, #ff512f, #dd2476);
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

                .google-btn {
                    text-decoration: none;
                    background: #DB4437;
                    color: white;
                    padding: 12px 24px;
                    border-radius: 6px;
                    display: inline-block;
                    font-size: 16px;
                    transition: 0.3s;
                }

                .google-btn:hover {
                    background: #c23321;
                    transform: scale(1.05);
                }
            </style>
        </head>
        <body>

            <div class="login-box">
                <h2>Agent Complaint System</h2>
                <p>Login using your Google Account</p>

                <a class="google-btn" href="/oauth2/authorization/google">
                    Login with Google
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

        String picture = user.getAttribute("picture") != null
                ? user.getAttribute("picture")
                : user.getAttribute("avatar_url");

        StringBuilder html = new StringBuilder();

        html.append("<!DOCTYPE html>")
                .append("<html>")
                .append("<head>")
                .append("<title>Agent Dashboard</title>")
                .append("<style>")
                .append("body{margin:0;padding:20px;min-height:100vh;background:linear-gradient(135deg,#667eea,#764ba2);font-family:Arial,sans-serif;}")
                .append(".container{background:white;padding:30px;border-radius:12px;max-width:900px;margin:auto;box-shadow:0 10px 25px rgba(0,0,0,0.3);}")
                .append(".header{text-align:center;margin-bottom:25px;}")
                .append(".header img{width:90px;height:90px;border-radius:50%;margin-bottom:10px;}")
                .append("table{width:100%;border-collapse:collapse;margin-top:25px;}")
                .append("th,td{padding:12px;text-align:center;border-bottom:1px solid #ddd;}")
                .append("th{background:#667eea;color:white;}")
                .append("tr:hover{background:#f2f2f2;}")
                .append(".logout-btn a{text-decoration:none;background:#e74c3c;color:white;padding:10px 22px;border-radius:6px;}")
                .append("</style>")
                .append("</head>")
                .append("<body>");

        html.append("<div class='container'>");

        html.append("<div class='header'>")
                .append("<h2>Welcome, ").append((String) user.getAttribute("name")).append("</h2>")
                .append("<p>Email: ").append((String) user.getAttribute("email")).append("</p>")
                .append("</div>");

        html.append("<h3 style='text-align:center;'>Agent List</h3>");

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
