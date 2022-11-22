package cn.weiyinfu.rubik;

import cn.weiyinfu.rubik.web.Controller;
import io.vertx.core.Vertx;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by chengen on 26/04/2017.
 */
@RunWith(VertxUnitRunner.class)
public class TestController {

private Vertx vertx;

@Before
public void setUp(TestContext context) {
    vertx = Vertx.vertx();
    vertx.deployVerticle(Controller.class.getName(), context.asyncAssertSuccess());
}

@After
public void tearDown(TestContext context) {
    vertx.close(context.asyncAssertSuccess());
}

@Test
public void testApplication(TestContext context) {
    final Async async = context.async();
    vertx.createHttpClient().getNow(Controller.port, "localhost", "/haha?one=1&two=2", response -> {
        response.handler(body -> {
            var json = body.toJsonObject();
            context.assertTrue(json.getInteger("sum").equals(json.getInteger("one") + json.getInteger("two")));
            async.complete();
        });
    });
}
}