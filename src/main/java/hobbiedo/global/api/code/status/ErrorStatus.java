package hobbiedo.global.api.code.status;

import org.springframework.http.HttpStatus;

import hobbiedo.global.api.code.BaseErrorCode;
import hobbiedo.global.api.dto.ErrorReasonDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorStatus implements BaseErrorCode {
	VALID_EXCEPTION(HttpStatus.BAD_REQUEST, "GLOBAL400", "데이터베이스 유효성 에러"),
	EXAMPLE_EXCEPTION(HttpStatus.BAD_REQUEST, "EXAMPLE400", "샘플 에러 메시지입니다"),

	// 회원 별 취미 데이터가 존재하지 않음
	GET_USER_HOBBIES_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_HOBBIES401",
		"해당 회원의 취미 리스트가 존재하지 않습니다. 취미 조사 후 이용해주세요."),
	// 회원 별 취미 데이터가 10개 미만
	GET_USER_HOBBIES_LESS(HttpStatus.NOT_FOUND, "USER_HOBBIES402",
		"회원 별 취미 데이터가 10개 미만입니다. 해당 회원의 취미를 더 추가해주세요."),

	// 취미 데이터가 존재하지 않음
	GET_HOBBY_NOT_FOUND(HttpStatus.NOT_FOUND, "HOBBY401",
		"해당 취미가 존재하지 않습니다. 취미를 다시 확인해주세요."),
	// 취미 데이터가 10개 미만
	GET_HOBBY_SIZE_LESS(HttpStatus.NOT_FOUND, "HOBBY402",
		"취미 데이터가 10개 미만입니다. 취미를 더 추가해주세요."),
	// 취미 데이터가 다 삭제되지 않음
	DELETE_HOBBY_NOT_ALL(HttpStatus.NOT_FOUND, "HOBBY403",
		"취미 데이터가 모두 삭제되지 않았습니다. 다시 시도해주세요."),

	// 취미 추천 설문 질문 리스트가 비어있음
	GET_HOBBY_SURVEY_QUESTIONS_EMPTY(HttpStatus.NOT_FOUND, "SURVEY401",
		"취미 추천 설문 질문 리스트가 비어있습니다."),
	// 취미 추천 설문 질문 리스트가 20개 미만
	GET_HOBBY_SURVEY_QUESTIONS_LESS(HttpStatus.NOT_FOUND, "SURVEY402",
		"취미 추천 설문 질문 리스트가 20개 미만입니다. 취미 추천 더 질문을 등록해주세요."),
	// 취미 추천 설문 응답 리스트가 비어있음
	HOBBY_SURVEY_QUESTIONS_EMPTY(HttpStatus.BAD_REQUEST, "SURVEY403",
		"취미 추천 설문 응답 리스트가 비어있습니다."),
	// 취미 추천 설문 응답 리스트가 20개 미만이거나 초과
	HOBBY_SURVEY_QUESTIONS_SIZE_LESS_OR_OVER(HttpStatus.BAD_REQUEST, "SURVEY405",
		"취미 추천 설문 응답 리스트가 20개 미만이거나 20개를 초과했습니다. 취미 추천 설문 응답을 20개로 맞춰주세요."),
	// 해당 취미 추천 설문 QuestionType이 존재하지 않음
	HOBBY_SURVEY_QUESTION_TYPE_NOT_FOUND(HttpStatus.NOT_FOUND, "SURVEY406",
		"해당 취미 추천 설문 QuestionType이 존재하지 않습니다. 취미 추천 설문 질문을 다시 확인해주세요.");

	private final HttpStatus httpStatus;
	private final String status;
	private final String message;

	/**
	 * getReason()함수를  사용해야 할 때
	 * 1. 간결성과 보안: API 응답에서 꼭 필요한 정보만을 포함시키기 위해 사용(ex: API 응답의 크기 감소, 민감한 데이터 제한)
	 * 2. 내부 처리용: API 응답 전송 전,내부 로그나 모니터링 시스템에 오류 정보를 기록할때 사용 로그 데이터의 크기를 줄이거나 처리를 단순화
	 * 3. HTTP 상태 코드 분리: getReason()을 사용하여 오류 코드와 메시지만을 전달하고, HTTP 상태 코드는 별도로 처리 가능
	 * 4. 성능 최적화: 성능이 중요한 환경에서는 가능한 한 응답을 간단하게 유지하여 이러한 오버헤드를 최소화
	 * 5. API 설계의 일관성: 오류 처리를 통합하여 여러 다른 API에서 일관된 방식으로 오류를 보고하고 싶을 때 유용
	 */
	@Override
	public ErrorReasonDto getReason() {
		return ErrorReasonDto
			.builder()
			.code(status)
			.message(message)
			.build();
	}

	@Override
	public ErrorReasonDto getReasonHttpStatus() {
		return ErrorReasonDto
			.builder()
			.httpStatus(httpStatus)
			.code(status)
			.message(message)
			.build();
	}
}
