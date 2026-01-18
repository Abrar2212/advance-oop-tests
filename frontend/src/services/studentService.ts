import axios from 'axios';
import type { Student, StudentFormData } from '../types/Student';

/**
 * Base URL for the backend API.
 * Change this if your backend runs on a different port or host.
 */
const API_BASE_URL = 'http://localhost:8080/api/students';

/**
 * Axios instance with default configuration.
 * Includes base URL and default headers.
 */
const apiClient = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

/**
 * Student Service - Handles all HTTP requests to the backend API.
 * Implements all CRUD operations: Create, Read, Update, Delete.
 */
const studentService = {
  /**
   * GET /api/students
   * Retrieve all students from the database.
   * 
   * @returns Promise with array of all students
   */
  getAllStudents: async (): Promise<Student[]> => {
    const response = await apiClient.get<Student[]>('/');
    return response.data;
  },

  /**
   * GET /api/students/{id}
   * Retrieve a single student by their ID.
   * 
   * @param id - The ID of the student to retrieve
   * @returns Promise with the student data
   */
  getStudentById: async (id: number): Promise<Student> => {
    const response = await apiClient.get<Student>(`/${id}`);
    return response.data;
  },

  /**
   * POST /api/students
   * Create a new student.
   * 
   * @param student - The student data to create
   * @returns Promise with the created student (includes generated ID)
   */
  createStudent: async (student: StudentFormData): Promise<Student> => {
    const response = await apiClient.post<Student>('/', student);
    return response.data;
  },

  /**
   * PUT /api/students/{id}
   * Update an existing student (full update - all fields required).
   * 
   * @param id - The ID of the student to update
   * @param student - The complete student data
   * @returns Promise with the updated student
   */
  updateStudent: async (id: number, student: StudentFormData): Promise<Student> => {
    const response = await apiClient.put<Student>(`/${id}`, student);
    return response.data;
  },

  /**
   * PATCH /api/students/{id}
   * Partially update a student (only specified fields are updated).
   * 
   * @param id - The ID of the student to update
   * @param updates - Object containing only the fields to update
   * @returns Promise with the updated student
   */
  partialUpdateStudent: async (id: number, updates: StudentFormData): Promise<Student> => {
    const response = await apiClient.patch<Student>(`/${id}`, updates);
    return response.data;
  },

  /**
   * DELETE /api/students/{id}
   * Delete a student by their ID.
   * 
   * @param id - The ID of the student to delete
   * @returns Promise that resolves when deletion is complete
   */
  deleteStudent: async (id: number): Promise<void> => {
    await apiClient.delete(`/${id}`);
  },
};

export default studentService;
