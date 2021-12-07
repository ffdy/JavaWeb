# 遇到的问题收集

- Filter 注解为 @WebFilter("路径")
- 添加过滤器后 Tomcat 启动报错：在项目结构lib里添加包 javax.servlet-api
- 重定向时遇到问题如下，原因是在一次响应里调用了两次重定向
```
类型 异常报告

消息 提交响应后无法调用sendRedirect（）。

描述 服务器遇到一个意外的情况，阻止它完成请求。

例外情况

java.lang.IllegalStateException: 提交响应后无法调用sendRedirect（）。
	org.apache.catalina.connector.ResponseFacade.sendRedirect(ResponseFacade.java:485)
	cn.edu.swu.ffdy.JavaWeb.Servlets.LoginServlet.doPost(LoginServlet.java:34)
	javax.servlet.http.HttpServlet.service(HttpServlet.java:681)
	javax.servlet.http.HttpServlet.service(HttpServlet.java:764)
	org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)
):注意 主要问题的全部 stack 信息可以在 server logs 里查看
```