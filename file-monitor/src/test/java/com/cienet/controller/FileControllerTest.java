/**
 * 
 */
package com.cienet.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author pengmingwang
 *
 */
// @RunWith(SpringRunner.class)
// @SpringBootTest(classes = FileManagerApp.class)
// @AutoConfigureMockMvc
public class FileControllerTest {
    @Autowired
    private MockMvc mvc;

    private RequestBuilder request;

    @Test
    public void testListResource() throws Exception {
        Map<String, String> param = new HashMap<String, String>();
        param.put("key", "aaa");

        String json = JSONObject.toJSONString(param);

        request = get("/cm/resource").contentType(MediaType.APPLICATION_JSON).content(json);
        MvcResult mvcResult = mvc.perform(request).andExpect(status().isOk()).andDo(print()).andReturn();

        String result = mvcResult.getResponse().getContentAsString();
        System.out.println(result);



    }

    public static String getElement(String json) {
        JSONObject result = JSON.parseObject(json);
        String data = result.getString("data");
        return data;
    }

    public static String getElement(String json, String element) {
        JSONObject jsonO = JSON.parseObject(json);
        return jsonO.getString(element);
    }

}
