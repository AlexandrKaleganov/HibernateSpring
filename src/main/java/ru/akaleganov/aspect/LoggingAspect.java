package ru.akaleganov.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import ru.akaleganov.service.ServiceAddObjects;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger LOGGER = Logger.getLogger(ServiceAddObjects.class);
    /**
     *   * возвращаемое значение любое
     *   ru.akaleganov.service.*.* -любые сервисы любые методы
     *   (..) - с любыми параметрами
     */
    @Pointcut("execution(* ru.akaleganov.service.*.*(..))")
    private void allLogEventMethods() {
    }

    @Pointcut("allLogEventMethods() && within(*.*File*Logger)")
    private void logEventInsideFileLoggers() {
    }
    @Before("allLogEventMethods()")   // используется до
    public void logBefore(JoinPoint joinPoint) {
        LOGGER.info("выполнился запрос: " + joinPoint.getTarget().getClass().getSimpleName() + " "
        + joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "allLogEventMethods()", returning = "ret")  // вместо адвайса returning можно использовать адвайс throwning
    public void logAfter(Object ret) {
        LOGGER.info("Возвращаемое значение value: " + ret);
    }
}
