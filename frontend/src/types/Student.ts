// Enum for student enrollment status (using const values for compatibility)
export const EnrollmentStatus = {
  ACTIVE: 'ACTIVE',
  INACTIVE: 'INACTIVE',
  GRADUATED: 'GRADUATED',
  SUSPENDED: 'SUSPENDED'
} as const;

export type EnrollmentStatus = typeof EnrollmentStatus[keyof typeof EnrollmentStatus];

// Student interface matching backend entity
export interface Student {
  id?: number;
  firstName: string;
  lastName: string;
  email: string;
  dateOfBirth: string;
  phoneNumber?: string;
  address?: string;
  major: string;
  gpa?: number;
  enrollmentStatus: EnrollmentStatus;
}

// Form data type for creating/updating students
export type StudentFormData = Partial<Student>;
