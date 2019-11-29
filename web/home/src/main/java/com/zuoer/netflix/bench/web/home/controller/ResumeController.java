/*
 * Copyright 2018 JamesZBL
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.zuoer.netflix.bench.web.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zuoer.netflix.bench.base.json.JsonOutputResult;
import com.zuoer.netflix.bench.core.model.Resume;
import com.zuoer.netflix.bench.core.request.ResumeModifyRequest;
import com.zuoer.netflix.bench.core.result.ResumeOperateResult;
import com.zuoer.netflix.bench.core.service.ResumeManageComponent;
import com.zuoer.netflix.bench.core.service.ResumeQueryComponent;
import com.zuoer.netflix.bench.web.home.controller.base.BaseController;
import com.zuoer.netflix.bench.web.home.form.ResumeModifyForm;

/**
 * @author JamesZBL
 * @date 2018-03-28
 */
@Controller
public class ResumeController extends BaseController {

	@Autowired
	private ResumeQueryComponent resumeQueryComponent;

	@Autowired
	private ResumeManageComponent resumeManageComponent;

	@ModelAttribute
	public Resume createResume() {
		return new Resume();
	}

	@GetMapping("/resume")
	public String getResume(Model model) {
		Resume resume = resumeQueryComponent.getLastOne();
		model.addAttribute("article", resume);
		return "resume";
	}

	@PostMapping("/update_resume.f")
	@ResponseBody
	public Object updateResume(ResumeModifyForm form, @ModelAttribute(binding = false) Resume resume) {
		JsonOutputResult jsonResult = new JsonOutputResult();

		ResumeModifyRequest request = new ResumeModifyRequest();
		request.setHtmlMaterial(form.getHtmlMaterial());
		request.setIntroduction(form.getDescription());
		request.setMdMaterial(form.getMdMaterial());
		request.setTitle(form.getTitle());
		request.setTagNameList(form.getRawTags());
		request.setId(form.getId());
		ResumeOperateResult resumeOperateResult = resumeManageComponent.modify(request);
		jsonResult.setSuccess(resumeOperateResult.isSuccess());
		jsonResult.setError(resumeOperateResult.getError());
		jsonResult.setDetailMessage(resumeOperateResult.getDetailMessage());

		return jsonResult;
	}
}
