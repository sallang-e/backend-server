package sallange.server.config.argumentresolver;

import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import sallange.server.auth.util.AuthTokensGenerator;
import sallange.server.auth.util.BearerParser;

@RequiredArgsConstructor
@Component
public class UserAuthArgumentResolver implements HandlerMethodArgumentResolver {

    private final AuthTokensGenerator authTokensGenerator;

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return parameter.getParameterType().equals(UserAuth.class);
    }

    @Override
    public Object resolveArgument(final MethodParameter parameter, final ModelAndViewContainer mavContainer, final NativeWebRequest webRequest, final WebDataBinderFactory binderFactory) throws Exception {
        final String authorizationHeader = webRequest.getHeader(HttpHeaders.AUTHORIZATION);
        final String accessToken = BearerParser.parseAuthorization(authorizationHeader);
        return new UserAuth(authTokensGenerator.extractUserId(accessToken));
    }
}
