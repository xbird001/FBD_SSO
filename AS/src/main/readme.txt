1、密码模式获取token的过程
    1.1、先由spring security的拦截器根据请求的请求头里面带的信息判断客户端的合法性，并生成Principal
    1.2、拦截器验证合法后，请求达到/auth/token的controller
        1.2.1、根据clientId获取客户端的信息ClientDetails;
        1.2.2、根据请求里面的请求参数(oauthe2协议密码模式规定的参数)和1.2.1中获取到的客户端信息ClientDetails生成TokenRequest;
        1.2.3、TokenGranter(CompositeTokenGranter)根据授权类型grand_type和TokenRequest生成授权的token
            1.2.3.1、CompositeTokenGranter根据根据grand_type授权类型从5种('authorization_code','refresh_token','implicit','client_credentials','password')授权模型对应的tokenGranter处理
            1.2.3.2、具体的tokenGranter的业务是由TokenService处理的(默认是DefaultTokenService),tokenService然后根据authenticationManager去验证请求中传入的用户名和密码(非客户端客户id和密码)，这里
            可理解成微信或者qq的账号和密码，如果验证通过则生成token(先从tokenStore里面拿，拿不到则生成)，然后将生成的token给TokenService里面的accessTokenEnhancer(如果有配置)进行进一步处理，如自定义token的生成策略
            (在生成token的过程中就一并将token进行了增强(如果配置了增强器))，处理完成后在由tokenStore
            进行存储(支持多种方式，本机内存、数据库、缓存)，默认是本机内存存储，供后面token验证时进行对应的查询验证
        1.2.4、将授权的token返回给前端

