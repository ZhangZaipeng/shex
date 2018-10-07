package com.exchange.platform.springmvc;

import com.exchange.platform.YvanUtil;
import com.exchange.platform.platform.JsonWapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class JsonView extends AbstractView {

  private static final Logger LOG = LoggerFactory.getLogger(JsonView.class);
  protected Object object;

  public JsonView(JsonWapper jw) {
    this.object = jw.getInnerMap();
  }

  public JsonView(Object object) {
    this.object = object;
  }

  @Override
  protected void renderMergedOutputModel(@SuppressWarnings("rawtypes") Map model,
      HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    String jsonString = (this.object == null ? "{}" : YvanUtil.toJson(this.object));

    boolean useGzip = ResponseUtils.isSupportGzip(request);
    try {
      ResponseUtils
          .writeJSONToResponse(response, ResponseUtils.DEFAULT_ENCODING, jsonString, useGzip,
              false);
    } catch (IOException e) {
      try {
        LOG.error(e.toString());
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      } catch (IOException e1) {
        throw new RuntimeException("system error.", e1);
      }
    }
  }

}
