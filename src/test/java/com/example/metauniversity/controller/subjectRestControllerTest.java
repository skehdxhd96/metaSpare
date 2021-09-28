package com.example.metauniversity.controller;

import com.amazonaws.services.ec2.model.UserData;
import com.example.metauniversity.config.MockSecurityFilter;
import com.example.metauniversity.domain.User.EnrollmentStatus;
import com.example.metauniversity.domain.User.User;
import com.example.metauniversity.domain.User.UserTyped;
import com.example.metauniversity.domain.User.UsersData;
import com.example.metauniversity.domain.subject.dto.subjectDto;
import com.example.metauniversity.security.CustomUserDetails;
import com.example.metauniversity.service.subjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.usertype.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static com.example.metauniversity.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureRestDocs
@AutoConfigureMockMvc
@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, properties = "spring.config.location="
        + "classpath:application.yml,"
        + "classpath:aws.yml")
public class subjectRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private subjectService subjectService;

    @Autowired
    private ObjectMapper objectMapper;

    private UsersData usersData;
    private User user;

    @BeforeEach
    public void setup(RestDocumentationContextProvider restDocumentation) {

        usersData = UsersData.builder()
                .userName("mockName")
                .userBirth("mockBirth")
                .userBirth("mockBirth")
                .userEmail("mockEmail")
                .Address("mockAddress")
                .userDepartment("userDepartment")
                .userMajor("mockMajor")
                .userMinor("mockMinor")
                .userType(UserTyped.STUDENT)
                .enrollmentStatus(EnrollmentStatus.ATTENDING)
                .userGrade(1)
                .isThumbnail(true)
                .workerSpot("mockSpot")
                .build();

        user = User.builder()
                .usersData(usersData)
                .accountId("mockAccountId")
                .userPassword("mockPassword")
                .confirmEmail("mockEmail")
                .build();

        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(documentationConfiguration(restDocumentation))
                .apply(springSecurity(new MockSecurityFilter()))
                .build();
    }

    @Test
    @DisplayName(value = "수강신청 조회")
    public void sugang() throws Exception{
        //given
        subjectDto.enroll enroll = subjectDto.enroll.builder()
                .subjectTitle("mockSubjectTitle")
                .status(true)
                .build();
        given(subjectService.EnrollSubject(any(), any())).willReturn(enroll);

        //when
        ResultActions result = mvc.perform(RestDocumentationRequestBuilders.post("/subject/enroll/{subjectId}", 1L)
                .principal(new UsernamePasswordAuthenticationToken(CustomUserDetails.create(user), null))
                .header("Authorization", "현재 로그인한 유저"));

        //then
        result
                .andDo(print())
                .andDo(document("subject-enroll",
                        getDocumentResponse(),
                        requestHeaders(
                                headerWithName("Authorization").description("현재 로그인한 유저")
                        ),
                        pathParameters(
                                parameterWithName("subjectId").description("수업 아이디")
                        ),
                        responseFields(
                                fieldWithPath("subjectTitle").type(JsonFieldType.STRING).description("과목 타이틀"),
                                fieldWithPath("status").type(JsonFieldType.BOOLEAN).description("수강신청 여부(true : 수강신청됨 / false : 수강취소됨)")
                        )
                ));
    }

    @Test
    @DisplayName(value = "수강취소 조회")
    public void sugangCancel() throws Exception {
        //given
        subjectDto.enroll enroll = subjectDto.enroll.builder()
                .subjectTitle("mockSubjectTitle")
                .status(false)
                .build();
        given(subjectService.cancelSubject(any(), any())).willReturn(enroll);

        //when
        ResultActions result = mvc.perform(RestDocumentationRequestBuilders.post("/subject/cancel/{subjectId}", 1L)
                .principal(new UsernamePasswordAuthenticationToken(CustomUserDetails.create(user), null))
                .header("Authorization", "현재 로그인한 유저"));

        //then
        result
                .andDo(print())
                .andDo(document("subject-cancel",
                        getDocumentResponse(),
                        requestHeaders(
                                headerWithName("Authorization").description("현재 로그인한 유저")
                        ),
                        pathParameters(
                                parameterWithName("subjectId").description("수업 아이디")
                        ),
                        responseFields(
                                fieldWithPath("subjectTitle").type(JsonFieldType.STRING).description("과목 타이틀"),
                                fieldWithPath("status").type(JsonFieldType.BOOLEAN).description("수강신청 여부(true : 수강신청됨 / false : 수강취소됨)")
                        )
                ));
    }

    @Test
    @DisplayName(value = "수강신청 필터링")
    public void sugangFilter() throws Exception {
        //given
        List<subjectDto.getList> glist = new ArrayList<>();
        glist.add(subjectDto.getList.builder()
                .subjectTitle("mockTitle")
                .subjectPoints(3)
                .professor("mockProfessor")
                .isMajor("all")
                .subjectDepaetment("mockDepartment")
                .subjectGrades(1)
                .limited(40)
                .classRoom("301")
                .day("Mon")
                .startTime("13:00")
                .endTime("15:00")
                .build());

        given(subjectService.getAllBySearch(any())).willReturn(glist);

        //when
        ResultActions result = mvc.perform(RestDocumentationRequestBuilders.get("/subject/list/search"));

        //then
        result
                .andDo(print())
                .andDo(document("subject-filter",
                        getDocumentResponse(),
                        requestParameters(
                                parameterWithName("subjectTitle").optional().description("과목 타이틀 : 포함하는 단어 검색"),
                                parameterWithName("subjectPoints").optional().description("과목 학점 : 입력한 학점과 같은 학점의 수업 검색"),
                                parameterWithName("isMajor").optional().description("전공 여부 : 전공 / 교양으로 나누어 검색")
                        ),
                        responseFields(
                                fieldWithPath("count").type(JsonFieldType.NUMBER).description("필터링된 과목 개수"),
                                fieldWithPath("data.[].subjectTitle").type(JsonFieldType.STRING).description("필터링된 과목 이름"),
                                fieldWithPath("data.[].subjectPoints").type(JsonFieldType.NUMBER).description("필터링된 과목 학점"),
                                fieldWithPath("data.[].professor").type(JsonFieldType.STRING).description("필터링된 과목 교수명"),
                                fieldWithPath("data.[].isMajor").type(JsonFieldType.STRING).description("필터링된 과목 전공여부"),
                                fieldWithPath("data.[].subjectDepaetment").type(JsonFieldType.STRING).description("필터링된 과목 개설대학"),
                                fieldWithPath("data.[].subjectGrades").type(JsonFieldType.NUMBER).description("필터링된 과목 대상학년"),
                                fieldWithPath("data.[].limited").type(JsonFieldType.NUMBER).description("필터링된 과목 제한인원"),
                                fieldWithPath("data.[].classRoom").type(JsonFieldType.STRING).description("필터링된 과목 강의실"),
                                fieldWithPath("data.[].day").type(JsonFieldType.STRING).description("필터링된 과목 수업요일"),
                                fieldWithPath("data.[].startTime").type(JsonFieldType.STRING).description("필터링된 과목 수업시작시간"),
                                fieldWithPath("data.[].endTime").type(JsonFieldType.STRING).description("필터링된 과목 수업마감시간")
                        )
                ))
                .andExpect(jsonPath("$.count").value(1))
                .andExpect(jsonPath("$.data.[0].subjectTitle").value("mockTitle"))
                .andExpect(jsonPath("$.data.[0].subjectPoints").value(3))
                .andExpect(jsonPath("$.data.[0].professor").value("mockProfessor"))
                .andExpect(jsonPath("$.data.[0].isMajor").value("all"))
                .andExpect(jsonPath("$.data.[0].subjectDepaetment").value("mockDepartment"))
                .andExpect(jsonPath("$.data.[0].subjectGrades").value(1))
                .andExpect(jsonPath("$.data.[0].limited").value(40))
                .andExpect(jsonPath("$.data.[0].classRoom").value("301"))
                .andExpect(jsonPath("$.data.[0].day").value("Mon"))
                .andExpect(jsonPath("$.data.[0].startTime").value("13:00"))
                .andExpect(jsonPath("$.data.[0].endTime").value("15:00"));
    }
}
