// Variables

const fixExtensionItemButtons = document.querySelectorAll(
  ".fix-extension-item .button"
);
const customExtensionInput = document.querySelector(
  ".custom-extension-input-area > input"
);
const fixExtensionInput = document.querySelector(
    ".fix-extension-input-area > input"
);
const addButton = document.querySelector(".add-button");
const addFixByButton = document.querySelector(".fix-extension-add-button");
const deleteFixByButton = document.querySelector(".fix-extension-delete-button");
let deleteButtons = document.querySelectorAll(".delete");


const resetButtons = document.querySelectorAll(".reset-button");

// Events

/**
 * 고정 확장자 체크박스 클릭 이벤트
 * */
fixExtensionItemButtons.forEach((checkbox) => {
  checkbox.addEventListener("click", function (event) {
    // 스타일 적용
    this.classList.toggle("active");

    // Ajax 요청
    const extensionName = this.name // 문자열 어떻게 가져올지 체크 필요
    let isChecked = this.classList.contains("active") ? "true" : "false";
    updateExtensionChecked(extensionName, isChecked);
  });
});


/**
 * 커스텀 확장자 입력 시 버튼 스타일 활성화
 */
customExtensionInput.addEventListener("input", function (event) {
  const inputValue = customExtensionInput.value;

  if (inputValue === "") {
    addButton.classList.remove("active");
  } else {
    addButton.classList.add("active");
  }
});
/**
 * 고정 확장자 추가 버튼 클릭 시 추가
 */
addFixByButton.addEventListener("click", function () {
  const newFixName = fixExtensionInput.value;
  const fixCount = document.querySelector(".fix-current").textContent;

  // 고정 확장자 수 20개 이상 등록 방지
  if (fixCount == 20) {
    fixExtensionInput.value = "";
    alert("고장 확장자 최대 저장 횟수를 초과하였습니다.");
  } else {
    if (extensionValidateCheck(newFixName)) {
      addFixExtension(newFixName);
    } else {
      alert("영문 대소문자를 입력해주세요! (한글, 공백, 특수문자 입력 불가)");
      fixExtensionInput.value = "";
    }
  }
});

/**
 * 커스텀 확장자 추가 버튼 클릭 시 추가
 */
addButton.addEventListener("click", function () {
  const newCustomName = customExtensionInput.value;
  const customCount = document.querySelector(".current").textContent;

  // 커스텀 수 200개 이상 등록 방지
  if (customCount == 200) {
    customExtensionInput.value = "";
    alert("커스텀 확장자 최대 저장 횟수를 초과하였습니다.");
  } else {
    if (extensionValidateCheck(newCustomName)) {
      addCustomExtension(newCustomName);
    } else {
      alert("영문 대소문자를 입력해주세요! (한글, 공백, 특수문자 입력 불가)");
      customExtensionInput.value = "";
    }
  }
});

/**
 * 커스텀 확장자 Enter 입력 시 추가
 */
customExtensionInput.addEventListener("keyup", function (event) {
  if (event.keyCode === 13) {
    event.preventDefault();
    addButton.click();
  }
});

/**
 * 고정 확장자 삭제 버튼 클릭 시 삭제
 * */
deleteFixByButton.addEventListener("click", function () {
    const fixExtensionName = fixExtensionInput.value;
  if (fixExtensionName) {
    extensionValidateCheck(fixExtensionName)
  }
  if (confirm("입력하신 고정 확장자를 리스트에서 삭제하시겠습니까?")) {
    deleteFixExtension(fixExtensionName);
  } else {
    return false;
  }

  });

/**
 * 커스텀 확장자 삭제 버튼 클릭 시 삭제
 * */
deleteButtons.forEach((delButton) => {
  delButton.addEventListener("click", function () {
    const extensionName = this.previousElementSibling.textContent;

    deleteCustomExtension(extensionName);
  });
});

/**
 * [고정 / 커스텀] 초기화 버튼 클릭 시 수행
 */
resetButtons.forEach((button) => {
  button.addEventListener("click", function () {
    if (this.classList.contains("fix")) {
      // DB 초기화
      resetFixedExtension();

      // 스타일 수정
      fixedExtensionItemButtons.forEach((item) => {
        item.classList.remove("active");
      });
    } else if (this.classList.contains("custom")) {
      if (confirm("삭제 시 복구할 수 없습니다.\n커스텀 확장자를 모두 삭제하시겠습니까?")) {
        // DB 초기화
        resetCustomExtension();
      } else {
        return false;
      }

      // 확장자 수 초기화
      const currentCount = document.querySelector(".current");
      const customExtensionList = document.querySelector(
        ".custom-extension-list"
      );
      customExtensionList.innerHTML = "";
      currentCount.textContent = 0;
    }
  });
});

/**
 * 파일 업로드 시 파일명 표기
 */
// fileInput.addEventListener("change", function () {
//   if (fileInput.files.length > 0) {
//     const fileName = fileInput.files[0].name;
//
//     // 표시 문구 변경 및 스타일 활성화
//     fileText.textContent = fileName;
//     submitButton.classList.add("active");
//   } else {
//     // 표시 문구 변경 및 스타일 활성화
//     fileText.textContent = "파일 선택하기";
//     submitButton.classList.remove("active");
//   }
// });
//
// /**
//  * 파일 업로드 제출 버튼 클릭 시 유효성 검사
//  */
// submitButton.addEventListener("click", function () {
//   const fileList = fileInput.files;
//
//   if (fileList.length > 0) {
//     const fileNameArray = fileList[0].name.split(".");
//     const extensionName = fileNameArray[fileNameArray.length - 1];
//
//     checkValidExtension(extensionName);
//   } else {
//     alert("파일을 선택해주세요");
//   }
// });

// Functions

/**
 * 고정 확장자 checked 필드 업데이트 요청
 * @param extensionName
 * @param isChecked
 */
function updateExtensionChecked(extensionName, isChecked) {
  $.ajax({
    url: "fix/check/",
    type: "PUT",
    data: JSON.stringify({ name: extensionName, isChecked: isChecked }),
    contentType: "application/json",
    success: function (result) {
      if (result === true) {
        console.log("고정 확장자 checked 업데이트 성공");
      } else {
        console.log("고정 확장자 checked 업데이트 실패");
      }
    },
    error: function () {
      console.log("updateExtensionChecked 요청 실패");
    },
  });
}

/**
 * 고정 확장자 초기화 요청
 */
function resetFixedExtension() {
  $.ajax({
    url: "fixed/reset",
    type: "POST",
    success: function (result) {
      if (result === true) {
        console.log("고정 확장자 초기화 성공");
      } else {
        console.log("고정 확장자 초기화 실패");
      }
    },
    error: function () {
      console.log("고정 확장자 초기화 요청 실패");
    },
  });
}

function resetCustomExtension() {
  console.log("resetCustomExtension 호출");

  $.ajax({
    url: "custom/reset/",
    type: "DELETE",
    success: function (result) {
      if (result === true) {
        console.log("커스텀 확장자 초기화 성공");
      } else {
        console.log("커스텀 확장자 초기화 실패");
      }
    },
    error: function () {
      console.log("커스텀 확장자 초기화 요청 실패");
    },
  });
}

/**
 * 커스텀 확장자 추가 요청
 * @param newCustomName
 */
function addCustomExtension(newCustomName) {
  $.ajax({
    url: "custom/",
    data: JSON.stringify({ name: newCustomName }),
    type: "POST",
    contentType: "application/json", // 추가된 부분
    success: function (result) {
      if (result === true) {
        console.log("커스텀 확장자 추가 성공");
        location.reload();
      } else {
        console.log("커스텀 확장자 추가 실패");
        customExtensionInput.value = "";
        alert("고정 확장자 기능을 이용해주세요!");
      }
    },
    error: function () {
      customExtensionInput.value = "";
      alert("이미 추가된 확장자입니다!");
      console.log("커스텀 확장자 추가 요청 실패");
    },
  });
}

/**
 * 고정 확장자 추가 요청
 * @param newFixName
 */
function addFixExtension(newFixName) {
  $.ajax({
    url: "fix/",
    data: JSON.stringify({ name: newFixName }),
    type: "POST",
    contentType: "application/json", // 추가된 부분
    success: function (result) {
      if (result === true) {
        console.log("고정 확장자 추가 성공");
        location.reload();
      } else {
        console.log("고정 확장자 추가 실패");
        customExtensionInput.value = "";
        // alert("고정 확장자 기능을 이용해주세요!");
      }
    },
    error: function () {
      customExtensionInput.value = "";
      alert("이미 추가된 확장자입니다!");
      console.log("커스텀 확장자 추가 요청 실패");
    },
  });
}

/**
 * 확장자명 유효성 검사
 * @param name
 * @returns boolean
 */
function extensionValidateCheck(name) {
  const regex = /^[a-zA-Z]+$/;

  if (regex.test(name)) {
    return true;
  } else {
    return false;
  }
}

/**
 * 고정 확장자 삭제 요청
 * @param extensionName
 */
function deleteFixExtension(extensionName) {
  $.ajax({
    url: "fix/",
    data: JSON.stringify({name: extensionName,}),
    type: "DELETE",
    contentType: "application/json", // 추가된 부분
    success: function () {
      console.log("fix 삭제 성공");
      location.reload();
    },
    error: function () {
      console.log("fix 삭제 실패");
    },
  });
}

/**
 * 커스텀 확장자 삭제 요청
 * @param extensionName
 */
function deleteCustomExtension(extensionName) {
  $.ajax({
    url: "custom/",
    data: JSON.stringify({name: extensionName,}),
    type: "DELETE",
    contentType: "application/json", // 추가된 부분
    success: function () {
      console.log("custom 삭제 성공");
      location.reload();
    },
    error: function () {
      console.log("custom 삭제 실패");
    },
  });
}

/**
 * 파일 제출 시 확장자명 유효성 검사
 * @param fileExtensionName
 */
// function checkValidExtension(fileExtensionName) {
//   $.ajax({
//     url: "checkValidExtension",
//     data: {
//       name: fileExtensionName,
//     },
//     type: "POST",
//     success: function (result) {
//       if (result === true) {
//         alert("제출 성공");
//       } else {
//         alert("제한된 확장자 입니다.");
//       }
//     },
//     error: function () {
//       console.log("checkValidExtension 실패");
//     },
//   });
// }
