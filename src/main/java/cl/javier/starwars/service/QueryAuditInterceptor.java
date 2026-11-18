package cl.javier.starwars.service;
import cl.javier.starwars.domain.QueryLog; import cl.javier.starwars.repository.QueryLogRepository; import jakarta.servlet.http.*; import org.springframework.security.core.Authentication; import org.springframework.stereotype.Component; import org.springframework.web.servlet.HandlerInterceptor; import java.util.regex.*;
@Component public class QueryAuditInterceptor implements HandlerInterceptor{
 private static final Pattern ID=Pattern.compile("/(characters|films)/(\\d+)"); private final QueryLogRepository logs; public QueryAuditInterceptor(QueryLogRepository logs){this.logs=logs;}
 public void afterCompletion(HttpServletRequest req,HttpServletResponse res,Object handler,Exception ex){Authentication a=(Authentication)req.getUserPrincipal();if(a==null)return;String path=req.getRequestURI();String type=path.contains("/characters")?"character":path.contains("/films")?"film":"data";Matcher m=ID.matcher(path);Long id=m.find()?Long.valueOf(m.group(2)):null;logs.save(new QueryLog(a.getName(),req.getMethod()+" "+path,type,id,req.getQueryString(),res.getStatus()));}
}
