package com.qihang.common.util.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * @author: qihang
 * @description:
 * @time: 2022-07-13 11:20
 */
@Slf4j
@Component
public class EmailUtils {


    @Resource
    private JavaMailSender mailSender;

    /**
     * 未出票发送邮件
     *
     * @param content            内容
     * @param receiveMailAccount 接收者
     * @param subject            主题
     */
    public void NoTicketIssuedSedEmail(String content, String receiveMailAccount, String subject) {

        String template = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "\n" +
                "	<head>\n" +
                "		<meta charset=\"UTF-8\">\n" +
                "		<title>Title</title>\n" +
                "	</head>\n" +
                "\n" +
                "	<body>\n" +
                "		<div style=\"background-color:#ECECEC; padding: 35px;\">\n" +
                "			<table cellpadding=\"0\" align=\"center\" style=\"width: 100%; margin: 0px auto; text-align: left; position: relative; border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 5px; border-bottom-left-radius: 5px; font-size: 14px; font-family:微软雅黑, 黑体; line-height: 1.5; box-shadow: rgb(153, 153, 153) 0px 0px 5px; border-collapse: collapse; background-position: initial initial; background-repeat: initial initial;background:#fff;\">\n" +
                "				<tbody>\n" +
                "					<tr>\n" +
                "						<th valign=\"middle\" style=\"height: 25px; line-height: 25px; padding: 15px 35px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #42a3d3; background-color: #49bcff; border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 0px; border-bottom-left-radius: 0px;\">\n" +
                "							<font face=\"微软雅黑\" size=\"5\" style=\"color: rgb(255, 255, 255); \">系统消息温馨提醒</font>\n" +
                "						</th>\n" +
                "					</tr>\n" +
                "					<tr>\n" +
                "						<td>\n" +
                "							<div style=\"padding:25px 35px 40px; background-color:#fff;\">\n" +
                "								<h5 style=\"margin: 5px 0px; \">\n" +
                "                        <font color=\"#333333\" style=\"line-height: 20px; \">\n" +
                "                            <font style=\"line-height: 22px; \" size=\"4\">\n" +
                "                            " + content + "<br /><br />\n" +
                "                            </font>\n" +
                "                        </font>\n" +
                "                    </h5>\n" +
                "							</div>\n" +
                "						</td>\n" +
                "					</tr>\n" +
                "				</tbody>\n" +
                "			</table>\n" +
                "		</div>\n" +
                "	</body>\n" +
                "\n" +
                "</html>";
        // 1. 创建一封邮件
        MimeMessage message = mailSender.createMimeMessage();
        try {
            // 2. From: 发件人                //需替换自己的邮箱
            message.setFrom(new InternetAddress("bieshujihua@163.com", "官方团队", "UTF-8"));

            // 3. To: 收件人（可以增加多个收件人、抄送、密送）
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMailAccount, "UTF-8"));

            // 4. Subject: 邮件主题
            message.setSubject(subject, "UTF-8");

            // 5. Content: 邮件正文（可以使用html标签）
            message.setContent(template, "text/html;charset=UTF-8");

            // 6. 设置发件时间
            message.setSentDate(new Date());

            // 7. 保存设置
            message.saveChanges();

            // 8. 发送
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
