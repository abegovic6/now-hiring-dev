export const get = async (url, params, token) => {
  token = token === undefined ? "" : token;
  if (params) {
    const response = await fetch(url + "?" + new URLSearchParams(params), {
      headers: { Authorization: "Bearer " + token },
    });
    return await response.json();
  }

  const response = await fetch(url, {
    headers: { Authorization: "Bearer " + token },
  });
  return await response.json();
};

export const post = async (url, body, params, token) => {
  token = token === undefined ? "" : token;
  if (body) {
    return executeRequest(url, params, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + token,
      },
      body: JSON.stringify(body),
    });
  }

  return executeRequest(url, params, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + token,
    },
  });
};

export const put = async (url, body, params, token) => {
  token = token === undefined ? "" : token;
  if (body) {
    return executeRequest(url, params, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + token,
      },
      body: JSON.stringify(body),
    });
  }

  return executeRequest(url, params, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + token,
    },
  });
};

export const deleteMet = async (url, params, token) => {
  token = token === undefined ? "" : token;
  if (params) {
    const response = await fetch(url + "?" + new URLSearchParams(params), {
      headers: { Authorization: "Bearer " + token },
    });
    return await response.json();
  }

  return executeRequest(url, params, {
    method: "DELETE",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + token,
    },
  });
};

const executeRequest = async (url, params, requestOptions) => {
  if (params) {
    let response = await fetch(
      url + "?" + new URLSearchParams(params),
      requestOptions
    );
    return await response.json();
  }
  let response = await fetch(url, requestOptions);
  return await response.json();
};
