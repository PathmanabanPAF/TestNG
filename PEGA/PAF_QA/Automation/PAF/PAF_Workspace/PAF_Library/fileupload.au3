; Wait 10 seconds for the Upload window to appear

  WinWait("[CLASS:#32770]","",10)
 
; Set input focus to the edit control of Upload window using the handle returned by WinWait
 
  ControlFocus("File Upload","","Edit1")
 
  Sleep(2000)
 
; Set the File name text on the Edit field
 
  ControlSetText("File Upload", "", "Edit1","E:\Pathmanaban_files\photo1.jpg")
 
  Sleep(2000)
 
; Click on the Open button
 
  ControlClick("File Upload", "","Button1");
