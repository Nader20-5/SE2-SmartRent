import api from "./api";

export const getAllUsers = async (params) => {
  const response = await api.get("/admin/users", { params });
  return response.data;
};

export const updateUserStatus = async (id, status) => {
  const response = await api.patch(`/admin/users/${id}/status`, { status });
  return response.data;
};

export const getPlatformStats = async () => {
  const response = await api.get("/admin/stats");
  return response.data;
};
