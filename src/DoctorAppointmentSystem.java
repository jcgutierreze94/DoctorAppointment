import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

class Doctor {
    private int id;
    private String name;
    private String speciality;

    public Doctor(int id, String nombre, String especialidad) {
        this.id = id;
        this.name = nombre;
        this.speciality = especialidad;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String especialidad) {
        this.speciality = especialidad;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", nombre='" + name + '\'' +
                ", especialidad='" + speciality + '\'' +
                '}';
    }
}

class Patient {
    private int id;
    private String name;

    public Patient(int id, String nombre) {
        this.id = id;
        this.name = nombre;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", nombre='" + name + '\'' +
                '}';
    }
}

class Appointment {
    private int id;
    private Date appointmentDate;
    private String motive;
    private Doctor doctor;
    private Patient patient;

    public Appointment(int id, Date FechaCita, String motivo, Doctor doctor, Patient paciente) {
        this.id = id;
        this.appointmentDate = FechaCita;
        this.motive = motivo;
        this.doctor = doctor;
        this.patient = paciente;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date FechaCita) {
        this.appointmentDate = FechaCita;
    }

    public String getMotive() {
        return motive;
    }

    public void setMotive(String motivo) {
        this.motive = motivo;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient paciente) {
        this.patient = paciente;
    }

    @Override
    public String toString() {
        return "Cita{" +
                "id=" + id +
                ", Fecha=" + appointmentDate +
                ", motivo='" + motive + '\'' +
                ", doctor=" + doctor +
                ", paciente=" + patient +
                '}';
    }
}

public class DoctorAppointmentSystem {
    private static List<Doctor> doctors = new ArrayList<>();
    private static List<Patient> patients = new ArrayList<>();
    private static List<Appointment> appointments = new ArrayList<>();

    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);
        System.out.println("Ingresa tu usuario:");
        String usuario = lector.nextLine();
        System.out.println("Ingresa tu password:");
        String password = lector.nextLine();

        if (!authenticateUser(usuario, password)) {
            System.out.println("Credenciales invalidas...");
            return;
        }

        loadAppointments();

        int choice;
        do {
            System.out.println("\n---------- Menu ----------");
            System.out.println("1. Registrar Doctor");
            System.out.println("2. Registrar Paciente");
            System.out.println("3. Crear Cita");
            System.out.println("4. Lista de Doctores");
            System.out.println("5. Lista de Pacientes");
            System.out.println("6. Lista de Citas");
            System.out.println("0. Guardar y salir");
            System.out.println("---------------------------");
            System.out.print("Ingresa la opcion de tu eleccion: ");
            choice = lector.nextInt();

            switch (choice) {
                case 1:
                    registerDoctor(lector);
                    break;
                case 2:
                    registerPatient(lector);
                    break;
                case 3:
                    createAppointment(lector);
                    break;
                case 4:
                    listDoctors();
                    break;
                case 5:
                    listPatients();
                    break;
                case 6:
                    listAppointments();
                    break;
                case 0:
                    saveAppointments();
                    System.out.println("Guardando y Saliendo...");
                    break;
                default:
                    System.out.println("Opcion invalida... Intenta de nuevo.");
            }
        } while (choice != 0);
    }

    private static boolean authenticateUser(String usuario, String password) {
        // Add your authentication logic here
        // For simplicity, let's assume username: admin, password: password
        return usuario.equals("jgutierr") && password.equals("Pass123");
    }

    private static void registerDoctor(Scanner lector) {
        System.out.print("Ingrese el ID del doctor: ");
        int id = lector.nextInt();
        lector.nextLine(); // Consume newline
        System.out.print("Ingrese el nombre del doctor: ");
        String nombre = lector.nextLine();
        System.out.print("Ingrese la especialidad del doctor: ");
        String especialidad = lector.nextLine();

        Doctor doctor = new Doctor(id, nombre, especialidad);
        doctors.add(doctor);

        System.out.println("Doctor registrado con exito");
    }

    private static void registerPatient(Scanner lector) {
        System.out.print("Ingrese el ID del paciente: ");
        int id = lector.nextInt();
        lector.nextLine(); // Consume newline
        System.out.print("Ingrese el nombre del paciente: ");
        String nombre = lector.nextLine();

        Patient patient = new Patient(id, nombre);
        patients.add(patient);

        System.out.println("Paciente registrado con exito");
    }

    private static void createAppointment(Scanner lector) {
        System.out.print("Enter appointment ID: ");
        int id = lector.nextInt();
        lector.nextLine(); // Consume newline
        System.out.print("Ingrese la fecha y hora de la cita con el siguiente formato (yyyy-MM-dd HH:mm): ");
        String dateString = lector.nextLine();
        System.out.print("Ingrese el motivo de la cita: ");
        String motivo = lector.nextLine();

        System.out.println("Doctores disponibles:");
        listDoctors();
        System.out.print("Ingrese el ID del doctor para la cita: ");
        int IdDoc = lector.nextInt();
        Doctor doctor = findDoctorById(IdDoc);

        System.out.println("Pacientes registrados:");
        listPatients();
        System.out.print("Ingrese el ID del paciente para asignar cita: ");
        int IdPaciente = lector.nextInt();
        Patient paciente = findPatientById(IdPaciente);

        Date FechaCita;
        try {
            FechaCita = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateString);
        } catch (ParseException e) {
            System.out.println("Formato invalido, creacion de la cita fallida");
            return;
        }

        Appointment cita = new Appointment(id, FechaCita, motivo, doctor, paciente);
        appointments.add(cita);

        System.out.println("Cita creada con exito");
    }

    private static void listDoctors() {
        if (doctors.isEmpty()) {
            System.out.println("No hay doctores registrados");
        } else {
            System.out.println("Doctores registrados:");
            for (Doctor doc : doctors) {
                System.out.println(doc);
            }
        }
    }

    private static void listPatients() {
        if (patients.isEmpty()) {
            System.out.println("No hay pacientes registrados");
        } else {
            System.out.println("Pacientes registrados:");
            for (Patient pacientes : patients) {
                System.out.println(pacientes);
            }
        }
    }

    private static void listAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No hay citas registradas");
        } else {
            System.out.println("Citas registradas:");
            for (Appointment cita : appointments) {
                System.out.println(cita);
            }
        }
    }

    private static Doctor findDoctorById(int id) {
        for (Doctor doc : doctors) {
            if (doc.getId() == id) {
                return doc;
            }
        }
        return null;
    }

    private static Patient findPatientById(int id) {
        for (Patient paciente : patients) {
            if (paciente.getId() == id) {
                return paciente;
            }
        }
        return null;
    }

    private static void loadAppointments() {
        try (BufferedReader lector = new BufferedReader(new FileReader("appointments.txt"))) {
            String line;
            boolean isDoctorsSection = false;
            boolean isPatientsSection = false;

            while ((line = lector.readLine()) != null) {
                if (line.equals("---DOCTORES---")) {
                    isDoctorsSection = true;
                    continue;
                } else if (line.equals("---PACIENTES---")) {
                    isDoctorsSection = false;
                    isPatientsSection = true;
                    continue;
                }

                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);

                if (isDoctorsSection) {
                    String nombre = parts[1];
                    String especialidad = parts[2];
                    Doctor doc = new Doctor(id, nombre, especialidad);
                    doctors.add(doc);
                } else if (isPatientsSection) {
                    String nombre = parts[1];
                    Patient paciente = new Patient(id, nombre);
                    patients.add(paciente);
                } else {
                    Date cita = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(parts[1]);
                    String motivo = parts[2];
                    int IdDoc = Integer.parseInt(parts[3]);
                    Doctor doc = findDoctorById(IdDoc);
                    int IdPaciente = Integer.parseInt(parts[4]);
                    Patient paciente = findPatientById(IdPaciente);

                    Appointment citaDoc = new Appointment(id, cita, motivo, doc, paciente);
                    appointments.add(citaDoc);
                }
            }
        } catch (IOException | ParseException e) {
            // Error handling
            e.printStackTrace();
        }
    }

    private static void saveAppointments() {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter("appointments.txt"))) {
            for (Appointment cita : appointments) {
                StringBuilder stbu = new StringBuilder();
                stbu.append(cita.getId()).append(",");
                stbu.append(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(cita.getAppointmentDate())).append(",");
                stbu.append(cita.getMotive()).append(",");

                Doctor doc = cita.getDoctor();
                if (doc != null) {
                    stbu.append(doc.getId());
                }
                stbu.append(",");

                Patient paciente = cita.getPatient();
                if (paciente != null) {
                    stbu.append(paciente.getId());
                }
                escritor.write(stbu.toString());
                escritor.newLine();
            }

            escritor.write("---DOCTORES---");
            escritor.newLine();
            for (Doctor doc : doctors) {
                StringBuilder strb = new StringBuilder();
                strb.append(doc.getId()).append(",");
                strb.append(doc.getName()).append(",");
                strb.append(doc.getSpeciality());
                escritor.write(strb.toString());
                escritor.newLine();
            }

            escritor.write("---PACIENTES---");
            escritor.newLine();
            for (Patient paciente : patients) {
                StringBuilder stb = new StringBuilder();
                stb.append(paciente.getId()).append(",");
                stb.append(paciente.getName());
                escritor.write(stb.toString());
                escritor.newLine();
            }
        } catch (IOException e) {
            // Error handling
            e.printStackTrace();
        }
    }
}