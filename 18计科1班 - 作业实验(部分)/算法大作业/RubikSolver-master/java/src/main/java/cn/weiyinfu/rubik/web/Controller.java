package cn.weiyinfu.rubik.web;

import cn.weiyinfu.rubik.three.min2phase.Min2PhaseSolver;
import cn.weiyinfu.rubik.cube.OperationList;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Launcher;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import cn.weiyinfu.rubik.two.TableSolver;
import io.vertx.ext.web.handler.impl.StaticHandlerImpl;
import io.vertx.ext.web.impl.Utils;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Controller extends AbstractVerticle {
public static int port = 9080;
TableSolver miniSolver = new TableSolver();
Min2PhaseSolver rubikSolver = new Min2PhaseSolver();
final String TEXT = "text/plain;charset=UTF-8";

@Override
public void start() {
    Router r = Router.router(vertx);
    r.route("/haha").handler(ctx -> {
        HttpServerRequest req = ctx.request();
        int one = Integer.parseInt(req.getParam("one"));
        int two = Integer.parseInt(req.getParam("two"));
        ctx.response().end(new JsonObject().put("one", one).put("two", two).put("sum", one + two).toString());
    });
    r.route("/debug").handler(ctx -> {
        String file = Utils.pathOffset("index.html", ctx);
        ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, TEXT).end(
                "currentDirectory" + Paths.get(".").toAbsolutePath().toString()
                        + "\n"
                        + "index.html:" + file
        );
    });
    r.route("/api/solve").handler(ctx -> {
        HttpServerRequest req = ctx.request();
        int N = Integer.parseInt(req.getParam("n"));
        String state = req.getParam("q");
        String ans = "";
        if (N == 2) {
            ans = miniSolver.solve(state);
            ans = new OperationList(ans).toFormatString();
        } else if (N == 3) {
            ans = rubikSolver.solve(state);
            ans = new OperationList(ans).toFormatString();
        } else {
            throw new RuntimeException("error N");
        }
        ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, TEXT).end(ans);
    });
    var staticHandler = StaticHandler.create();
    staticHandler.setAllowRootFileSystemAccess(true);
    staticHandler.setAlwaysAsyncFS(true);
    staticHandler.setCachingEnabled(false);
    r.route("/*").handler(staticHandler);
    vertx.createHttpServer().requestHandler(r).listen(port, "0.0.0.0", (server) -> {
        System.out.println("listening at http://localhost:" + port + "/haha?one=1&two=2");
    });
}

public static void main(String[] args) {
    System.out.println(Controller.class.getCanonicalName());
    Launcher.main(new String[]{
            "run",
            Controller.class.getCanonicalName(),
            "--redeploy=**/*",
            "--launcher-class=io.vertx.core.Launcher"});
}
}