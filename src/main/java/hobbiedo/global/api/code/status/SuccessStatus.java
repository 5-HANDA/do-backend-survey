package hobbiedo.global.api.code.status;

import org.springframework.http.HttpStatus;

import hobbiedo.global.api.code.BaseCode;
import hobbiedo.global.api.dto.ReasonDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessStatus implements BaseCode {
	EXAMPLE_EXCEPTION(HttpStatus.OK, "EXAMPLE200", "샘플 성공 메시지입니다."),

	// 회원 별 취미 조회 성공
	GET_USER_HOBBIES_SUCCESS(HttpStatus.OK, "200", "해당 회원의 취미 리스트 조회를 성공하였습니다."),
	// 나머지 회원 취미 추천 조회 성공
	GET_REMAINING_USER_HOBBIES_SUCCESS(HttpStatus.OK, "200", "해당 회원의 나머지 취미 추천 리스트 조회를 성공하였습니다."),
	// 회원 별 취미 카드 리스트 조회 성공
	GET_USER_HOBBY_CARDS_SUCCESS(HttpStatus.OK, "200", "해당 회원의 취미 카드 리스트 조회를 성공하였습니다."),

	// 취미 추천 설문 조회 성공
	GET_HOBBY_SURVEY_SUCCESS(HttpStatus.OK, "200", "취미 추천 설문 조회를 성공하였습니다."),

	// 취미 추천 설문 응답에 따른 회원 별 추천 취미 저장 및 수정 성공
	SAVE_USER_HOBBY_SUCCESS(HttpStatus.OK, "200", "회원 별 추천 취미 저장 또는 수정을 성공하였습니다.");

	private final HttpStatus httpStatus;
	private final String status;
	private final String message;

	/**
	 * getReason() 함수를 사용해야 할 때
	 * 1. 간결성과 보안: API 응답에서 꼭 필요한 정보만을 포함시키기 위해 사용(ex: API 응답의 크기 감소, 민감한 데이터 제한)
	 * 2. 내부 처리용: API 응답 전송 전,내부 로그나 모니터링 시스템에 오류 정보를 기록할때 사용 로그 데이터의 크기를 줄이거나 처리를 단순화
	 * 3. HTTP 상태 코드 분리: getReason()을 사용하여 오류 코드와 메시지만을 전달하고, HTTP 상태 코드는 별도로 처리 가능
	 * 4. 성능 최적화: 성능이 중요한 환경에서는 가능한 한 응답을 간단하게 유지하여 이러한 오버헤드를 최소화
	 * 5. API 설계의 일관성: 오류 처리를 통합하여 여러 다른 API에서 일관된 방식으로 오류를 보고하고 싶을 때 유용
	 */
	@Override
	public ReasonDto getReason() {
		return ReasonDto
				.builder()
				.code(status)
				.message(message)
				.build();
	}

	@Override
	public ReasonDto getReasonHttpStatus() {
		return ReasonDto
				.builder()
				.httpStatus(httpStatus)
				.code(status)
				.message(message)
				.build();
	}
}
