Imports System.Data.SqlClient

Public Class Form1
    Dim con As New SqlConnection
    Dim cmd As New SqlCommand

    Private Sub Form1_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        con.ConnectionString = "Data Source=(LocalDB)\MSSQLLocalDB;AttachDbFilename=C:\Users\koshe\source\repos\WindowsApp5\WindowsApp5\Database1.mdf;Integrated Security=True"
        If con.State = ConnectionState.Open Then
            con.Close()
        End If
        con.Open()
        disp_data()
    End Sub

    Public Sub disp_data()
        cmd = con.CreateCommand()
        cmd.CommandType = CommandType.Text
        cmd.CommandText = "Select * From table1"
        cmd.ExecuteNonQuery()
        Dim dt As New DataTable()
        Dim da As New SqlDataAdapter(cmd)
        da.Fill(dt)
        DataGridView1.DataSource = dt
    End Sub

    Private Sub Button1_Click(sender As Object, e As EventArgs) Handles Button1.Click
        cmd = con.CreateCommand()
        cmd.CommandType = CommandType.Text
        cmd.CommandText = "insert into table1 Values('" + TextBox1.Text + "','" + TextBox2.Text + "','" + TextBox3.Text + "','" + DateTimePicker1.Text + "')"
        cmd.ExecuteNonQuery()
        disp_data()
        MsgBox("Data Inserted")

    End Sub
End Class