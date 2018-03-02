/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.trolsoft.makebuilder;

import java.io.IOException;
import java.util.logging.Logger;
import org.netbeans.core.spi.multiview.MultiViewElement;
import org.netbeans.core.spi.multiview.text.MultiViewEditorElement;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.MIMEResolver;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.MultiFileLoader;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

@Messages({
	"LBL_MB_LOADER=Files of make.builder"
})
@MIMEResolver.ExtensionRegistration(
		  displayName = "#LBL_MB_LOADER",
		  mimeType = "text/x-builder",
		  extension = {"builder"}
)
@DataObject.Registration(
		  mimeType = "text/x-builder",
		  iconBase = "ru/trolsoft/makebuilder/builder_script.png",
		  displayName = "#LBL_MB_LOADER",
		  position = 10
)
@ActionReferences({
	@ActionReference(
			  path = "Loaders/text/x-builder/Actions",
			  id = @ActionID(category = "System", id = "org.openide.actions.OpenAction"),
			  position = 10,
			  separatorAfter = 200
	)
	,
    @ActionReference(
			  path = "Loaders/text/x-builder/Actions",
			  id = @ActionID(category = "Edit", id = "org.openide.actions.CutAction"),
			  position = 300
	)
	,
    @ActionReference(
			  path = "Loaders/text/x-builder/Actions",
			  id = @ActionID(category = "Edit", id = "org.openide.actions.CopyAction"),
			  position = 400,
			  separatorAfter = 500
	)
	,
    @ActionReference(
			  path = "Loaders/text/x-builder/Actions",
			  id = @ActionID(category = "Edit", id = "org.openide.actions.DeleteAction"),
			  position = 600
	)
	,
    @ActionReference(
			  path = "Loaders/text/x-builder/Actions",
			  id = @ActionID(category = "System", id = "org.openide.actions.RenameAction"),
			  position = 700,
			  separatorAfter = 800
	)
	,
    @ActionReference(
			  path = "Loaders/text/x-builder/Actions",
			  id = @ActionID(category = "System", id = "org.openide.actions.SaveAsTemplateAction"),
			  position = 900,
			  separatorAfter = 1000
	)
	,
    @ActionReference(
			  path = "Loaders/text/x-builder/Actions",
			  id = @ActionID(category = "System", id = "org.openide.actions.FileSystemAction"),
			  position = 1100,
			  separatorAfter = 1200
	)
	,
    @ActionReference(
			  path = "Loaders/text/x-builder/Actions",
			  id = @ActionID(category = "System", id = "org.openide.actions.ToolsAction"),
			  position = 1300
	)
	,
    @ActionReference(
			  path = "Loaders/text/x-builder/Actions",
			  id = @ActionID(category = "System", id = "org.openide.actions.PropertiesAction"),
			  position = 1400
	)
})
public class MBDataObject extends MultiDataObject {

	public MBDataObject(FileObject pf, MultiFileLoader loader) throws DataObjectExistsException, IOException {
		super(pf, loader);
		registerEditor("text/x-builder", true);
	}

	@Override
	protected int associateLookup() {
		return 1;
	}

	@MultiViewElement.Registration(
			  displayName = "#LBL_MB_EDITOR",
			  iconBase = "ru/trolsoft/makebuilder/builder_script.png",
			  mimeType = "text/x-builder",
			  persistenceType = TopComponent.PERSISTENCE_ONLY_OPENED,
			  preferredID = "MB",
			  position = 10
	)
	@Messages("LBL_MB_EDITOR=Source")
	public static MultiViewEditorElement createEditor(Lookup lkp) {
		return new MultiViewEditorElement(lkp);
	}

}
